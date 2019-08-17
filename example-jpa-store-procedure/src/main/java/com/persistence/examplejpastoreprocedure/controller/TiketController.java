package com.persistence.examplejpastoreprocedure.controller;

import com.persistence.examplejpastoreprocedure.dao.TiketDao;
import com.persistence.examplejpastoreprocedure.enity.Tiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tiket")
public class TiketController {

    @Autowired
    private TiketDao tiketDao;

    @GetMapping
    public ResponseEntity<List<Tiket>> allTikets(){
        List<Tiket> tikets = tiketDao.getAllTickets();
        if (tikets == null || tikets.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tikets, HttpStatus.OK);
    }

    @GetMapping(value = "/by")
    public ResponseEntity<List<Tiket>> tiketByCategory(@RequestParam(value = "category")String category){
        List<Tiket> tikets = tiketDao.getTiketByCategory(category);
        if (tikets == null || tikets.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tikets, HttpStatus.OK);
    }
}
