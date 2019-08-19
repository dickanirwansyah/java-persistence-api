package com.persistence.examplecartjpa.repository;

import com.persistence.examplecartjpa.entity.TransactionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Integer> {
}
