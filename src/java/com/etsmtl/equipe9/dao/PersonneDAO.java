package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Personne;
import java.util.List;


public class PersonneDAO extends DAOAbstrait<Personne, Long>{

    @Override
    public Personne findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personne findById(Long id) {
        return this.emFind(Personne.class, id);
    }
    
    @Override
    public List<Personne> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
