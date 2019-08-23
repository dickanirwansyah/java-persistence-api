package com.persistence.examplecartjpa.controller.api;

import com.persistence.examplecartjpa.entity.Transaction;
import com.persistence.examplecartjpa.model.TransactionResponseModel;
import com.persistence.examplecartjpa.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionalService transactionalService;

    @GetMapping(value = "/{transId}")
    public ResponseEntity<TransactionResponseModel> responseTransactionById(@PathVariable("transId")int transId){
        TransactionResponseModel responseModel = transactionalService.getTransactionById(transId);
        if (responseModel == null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
