package com.persistence.examplecartjpa.repository;

import com.persistence.examplecartjpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Product findById(int id);

    Product findProductById(int id);
}
