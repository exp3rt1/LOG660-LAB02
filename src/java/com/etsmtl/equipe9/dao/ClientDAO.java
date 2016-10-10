/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Client;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Nicolas Desktop
 */
public class ClientDAO {

    public Client getClient(String courriel) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        Client cli =  em.find(Client.class, courriel);
        em.close();
        emf.close();
        
        return cli;
    }
}
