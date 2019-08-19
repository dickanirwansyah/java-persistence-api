package com.persistence.examplecartjpa.controller.api;

import com.persistence.examplecartjpa.entity.Product;
import com.persistence.examplecartjpa.entity.TransactionProduct;
import com.persistence.examplecartjpa.model.TransactionAction;
import com.persistence.examplecartjpa.model.TransactionModel;
import com.persistence.examplecartjpa.model.TransactionProductModel;
import com.persistence.examplecartjpa.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/add-to-cart/{productId}")
    public TransactionModel addToCart(@PathVariable("productId")int productId){

        Product product = this.findByProductId(productId);
        TransactionModel transactionModel = new TransactionModel();
        List<TransactionProductModel> transactionProductModels = null;
        List<TransactionAction> transactionActions = new ArrayList<>();

        if (transactionActions.isEmpty()){
            transactionProductModels = new ArrayList<>();
            transactionProductModels.add(new TransactionProductModel(product, product.getPrice(), 1));
            transactionActions.add(new TransactionAction(product, product.getPrice(), 1));
            transactionModel.setTransactionProductModels(transactionProductModels);
        }else{
            transactionProductModels.equals(transactionActions);
            int index = this.productIsExists(productId, transactionProductModels);
            if (index == -1){
                transactionProductModels.add(new TransactionProductModel(product, product.getPrice(), 1));
            }else{
                int qty = transactionProductModels.get(index).getQty() + 1;
                transactionProductModels.get(index).setQty(qty);
            }
            transactionModel.setTransactionProductModels(transactionProductModels);
        }

        return transactionModel;
    }

    private int productIsExists(int id, List<TransactionProductModel> productModels){
        for (int i=0; i < productModels.size(); i++){
            if (String.valueOf(productModels.get(i).getProduct().getId()).equals(id)){
                return i;
            }
        }
        return -1;
    }

    private Product findByProductId(int productId){
        Product product = productRepository.findProductById(productId);
        if (String.valueOf(product.getId()).equalsIgnoreCase(String.valueOf(productId))){
            return product;
        }
        return null;
    }
}
