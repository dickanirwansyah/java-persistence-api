package com.persistence.examplejpastoreprocedure.dao;

import com.persistence.examplejpastoreprocedure.enity.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Repository
public class ProdukDao {

    @Autowired
    private EntityManager entityManager;

    public List<Produk> getProduks(){
        return entityManager.createNamedStoredProcedureQuery("produks").getResultList();
    }
}
