package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Pays;
import java.util.List;
import javax.persistence.Query;

public class PaysDAO extends DAOAbstrait<Pays, Long>{

    @Override
    public List<Pays> findAll() {
       
        try {
            connect();
             Query query = em.createQuery("SELECT p FROM Pays p ORDER BY p.nom asc");
             List<Pays> listePays = query.getResultList();
             return listePays;
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public Pays findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pays> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(Pays obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Pays obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Pays obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
