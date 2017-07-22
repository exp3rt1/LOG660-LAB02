package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Personne;
import java.util.List;


public class PersonneDAO extends DAOAbstrait<Personne, Long>{

    @Override
    public List<Personne> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Personne findById(Long id) {
        try {
            connect();
            return this.em.find(Personne.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }
    
    @Override
    public List<Personne> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
