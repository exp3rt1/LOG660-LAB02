/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Personne;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Nicolas Desktop
 */
public class PersonneDAO {

    public Personne getPersonne(BigDecimal personId) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        Personne per =  em.find(Personne.class, personId);
        em.close();
        emf.close();
        
        return per;
    }
}
