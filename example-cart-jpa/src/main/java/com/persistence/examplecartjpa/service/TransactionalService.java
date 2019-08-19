package com.persistence.examplecartjpa.service;

import com.persistence.examplecartjpa.repository.ProductRepository;
import com.persistence.examplecartjpa.repository.TransactionProductRepository;
import com.persistence.examplecartjpa.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public void insertTransaction(){

    }
}
