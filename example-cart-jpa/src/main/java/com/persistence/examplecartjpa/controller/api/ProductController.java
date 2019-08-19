package com.persistence.examplecartjpa.controller.api;

import com.persistence.examplecartjpa.entity.Product;
import com.persistence.examplecartjpa.model.ProductModelInsert;
import com.persistence.examplecartjpa.model.ProductModelUpdate;
import com.persistence.examplecartjpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listProduct(){
        return productService.listProduct();
    }


    @GetMapping(value = "/by")
    public Product getProductId(@RequestParam("productId")int productId){
        return productService.getIdProduct(productId);
    }

    @PostMapping(value = "/{categoryId}")
    public Product createProduct(@RequestBody @Valid ProductModelInsert productModelInsert,
                                 @PathVariable("categoryId") Integer categoryId){

        return productService.createProduct(productModelInsert, categoryId);
    }

    @PutMapping(value = "/{categoryId}/{productId}")
    public Product updateProduct(@RequestBody @Valid ProductModelUpdate productModelUpdate,
                                 @PathVariable(value = "categoryId")int categoryId,
                                 @PathVariable(value = "productId")int productId){

        return productService.updateProduct(productModelUpdate, categoryId, productId);
    }
}
