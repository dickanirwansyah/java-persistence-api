package com.persistence.examplecartjpa.service;

import com.persistence.examplecartjpa.entity.Transaction;
import com.persistence.examplecartjpa.entity.TransactionProduct;
import com.persistence.examplecartjpa.exception.ResourceNotFound;
import com.persistence.examplecartjpa.model.TransactionModel;
import com.persistence.examplecartjpa.model.TransactionProductModel;
import com.persistence.examplecartjpa.model.TransactionResponseModel;
import com.persistence.examplecartjpa.repository.ProductRepository;
import com.persistence.examplecartjpa.repository.TransactionProductRepository;
import com.persistence.examplecartjpa.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Component
public class TransactionalService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionProductRepository transactionProductRepository;

    @Transactional
    public TransactionResponseModel getTransactionById(int id){

        TransactionResponseModel transactionResponseModel = null;
        Transaction byid = null;
        try{

            byid = transactionRepository.findTransactionById(id);
            List<TransactionProduct> transactionProducts =  transactionProductRepository.findByTransaction(byid.getId());

            if (transactionProducts.isEmpty() || transactionProducts.size() == 0){
                throw new ResourceNotFound("data notfound");
            }

            transactionResponseModel = TransactionResponseModel
                    .builder()
                    .id(byid.getId())
                    .createdBy(byid.getCreatedBy())
                    .transactionProducts(transactionProducts)
                    .build();

        }catch (RuntimeException e){
            e.getMessage();
        }

        return transactionResponseModel;
    }

    @Transactional
    public void insertTransaction(TransactionModel transactionModel,HttpServletRequest request){

        try{

            Transaction transaction = Transaction.builder()
                    .createdBy(transactionModel.getCreatedBy())
                    .createdAt(new Date())
                    .build();

            transactionRepository.save(transaction);

            List<TransactionProductModel> productModels = (List<TransactionProductModel>) request.getSession().getAttribute("cart");
            for (int i=0; i < productModels.size(); i++){

                TransactionProduct transactionProduct = new TransactionProduct();

                transactionProduct.setTransaction(transaction);
                transactionProduct.setProduct(productModels.get(i).getProduct());
                transactionProduct.setPrice(productModels.get(i).getPrice());
                transactionProduct.setQty(productModels.get(i).getQty());

                transactionProductRepository.save(transactionProduct);

                System.out.println(String.format("INSERT DATA : "+
                                "| %s | %s | %s |",
                        productModels.get(i).getProduct().getName(),
                        productModels.get(i).getQty(),
                        productModels.get(i).getPrice()));
            }

        }catch (RuntimeException e){
            e.getMessage();
        }

    }
}
