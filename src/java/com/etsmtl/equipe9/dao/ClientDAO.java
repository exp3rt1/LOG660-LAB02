package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.service.IDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ClientDAO implements IDAO{

    

    public Client findById(String courriel) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        Client cli =  em.find(Client.class, courriel);
        em.close();
        emf.close();
        
        return cli;
    
}

    @Override
    public Object findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
