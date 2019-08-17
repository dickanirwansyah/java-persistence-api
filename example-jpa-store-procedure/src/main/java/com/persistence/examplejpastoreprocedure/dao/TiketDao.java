package com.persistence.examplejpastoreprocedure.dao;

import com.persistence.examplejpastoreprocedure.enity.Tiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Service
public class TiketDao {

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Tiket> getAllTickets(){
        return entityManager.createNamedStoredProcedureQuery("allTikets").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Tiket> getTiketByCategory(String sCategory){
        return entityManager.createNamedStoredProcedureQuery("tiketByCategory")
                .setParameter("sCategory", sCategory)
                .getResultList();
    }
}
