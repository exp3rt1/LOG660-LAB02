package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.service.IDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FilmDAO implements IDAO{

    @Override
    public Object findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Film findById(Long id) {
        
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        Film film =  em.find(Film.class, id);
        em.close();
        emf.close();
        
        return film;
    }
    
    @Override
    public boolean insert(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
