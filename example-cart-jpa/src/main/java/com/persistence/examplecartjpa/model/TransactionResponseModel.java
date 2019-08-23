package com.persistence.examplecartjpa.model;

import com.persistence.examplecartjpa.entity.TransactionProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseModel {

    private int id;
    private String createdBy;
    private List<TransactionProduct> transactionProducts;


}
