package com.persistence.examplecartjpa.model;

import com.persistence.examplecartjpa.constant.MessageConstan;
import com.persistence.examplecartjpa.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class TransactionModel {

    private String createdBy;

    private List<TransactionProductModel> transactionProductModels = new ArrayList<>();

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

    @Override
    public String toString() {
        return "TransactionModel{" +
                "createdBy='" + createdBy + '\'' +
                ", transactionProductModels=" + transactionProductModels +
                '}';
    }
}
