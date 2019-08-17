package com.persistence.examplejpastoreprocedure.controller;

import com.persistence.examplejpastoreprocedure.dao.ProdukDao;
import com.persistence.examplejpastoreprocedure.enity.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/produk")
public class ProdukController {

    @Autowired
    private ProdukDao produkDao;

    @GetMapping
    public List<Produk> getProduk(){
        return produkDao.getProduks();
    }
}
