package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Client;
import java.util.List;


public class ClientDAO extends DAOAbstrait<Client, String>{
    
    @Override
    public Client findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Client findById(String courriel) {
        return this.emFind(Client.class, courriel);
    }
    
    @Override
    public List<Client> findById(List<String> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
