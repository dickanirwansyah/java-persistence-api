package com.persistence.examplecartjpa.model;

import com.persistence.examplecartjpa.constant.MessageConstan;
import com.persistence.examplecartjpa.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class TransactionModel {

    @NotBlank(message = MessageConstan.dataNull)
    private String createdBy;

    private List<TransactionProductModel> transactionProductModels;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<TransactionProductModel> getTransactionProductModels() {
        return transactionProductModels;
    }

    public void setTransactionProductModels(List<TransactionProductModel> transactionProductModels) {
        this.transactionProductModels = transactionProductModels;
    }


    private TransactionProductModel findProductIdInTransaction(int id){
        for (TransactionProductModel productModel : this.transactionProductModels){
            if (String.valueOf(productModel.getProduct().getId()).equals(id)){
                return productModel;
            }
        }
        return null;
    }

    public void addProductIdInTransaction(Product product, int id){
        TransactionProductModel productModel = this.findProductIdInTransaction(product.getId());
        if (productModel == null){

            productModel = new TransactionProductModel();
            productModel.setQty(0);
            productModel.setPrice(productModel.getProduct().getPrice());
            productModel.setProduct(product);
            this.transactionProductModels.add(productModel);
        }

        int newQty = productModel.getQty() + 1;
        if (newQty <= 0){
            this.transactionProductModels.remove(product);
        }else{
            productModel.setQty(newQty);
        }
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "createdBy='" + createdBy + '\'' +
                ", transactionProductModels=" + transactionProductModels +
                '}';
    }
}
