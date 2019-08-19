package com.persistence.examplecartjpa.service;

import com.persistence.examplecartjpa.entity.Category;
import com.persistence.examplecartjpa.entity.Product;
import com.persistence.examplecartjpa.exception.ResourceNotFound;
import com.persistence.examplecartjpa.model.ProductModelInsert;
import com.persistence.examplecartjpa.model.ProductModelUpdate;
import com.persistence.examplecartjpa.repository.CategoryRepository;
import com.persistence.examplecartjpa.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Component
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Product> listProduct(){
        return productRepository.findAll(sortAscendingByCreatedAt());
    }

    public Product getIdProduct(int productId){
        return productRepository.findById(productId)
                .map(product -> {
                    product.setId(productId);
                    return product;
                }).orElseThrow(()-> new ResourceNotFound("product id : "+productId+" notfound"));
    }

    public Product createProduct(ProductModelInsert productModelInsert, int categoryId){

        return productRepository.save(Product.builder()
                .name(productModelInsert.getName())
                .stock(productModelInsert.getStock())
                .price(productModelInsert.getPrice())
                .createdAt(new Date())
                .updatedAt(new Date())
                .category(categoryById(categoryId))
                .build());

    }


    public Product updateProduct(ProductModelUpdate productUpdate, int productId, int categoryId){

        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFound("category id : "+categoryId+" notfound !");
        }

        return productRepository.findById(productId).map(product -> {
            product.setName(productUpdate.getName());
            product.setPrice(productUpdate.getPrice());
            product.setStock(productUpdate.getStock());
            product.setCreatedAt(productUpdate.getCreatedAt());
            product.setUpdatedAt(new Date());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFound("product id : "+productId+" notfound !"));

    }

    private Sort sortAscendingByCreatedAt(){
        return new Sort(Sort.Direction.ASC, "createdAt");
    }

    private Category categoryById(int categoryId){
        return categoryRepository.findById(categoryId);
    }

}
