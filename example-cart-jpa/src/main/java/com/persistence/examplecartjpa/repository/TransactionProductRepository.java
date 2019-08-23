package com.persistence.examplecartjpa.repository;

import com.persistence.examplecartjpa.entity.TransactionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Integer> {


    @Query("SELECT tp FROM TransactionProduct tp WHERE tp.transaction.id = :id")
    List<TransactionProduct> findByTransaction(@Param("id")int id);

}
