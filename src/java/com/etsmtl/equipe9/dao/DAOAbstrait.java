
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.service.IDAO;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DAOAbstrait<T,PK> implements IDAO<T,PK> {
    
    public final String PERSISTANCE_UNIT_NAME = "WebApplication1PU";
    public EntityManagerFactory emf;
    public EntityManager em;
    
    public void connect(){
        this.emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
        this.em = emf.createEntityManager();
    }
    
    public void disconnect(){
        if (em.isOpen()) { em.close();}
        if (emf.isOpen()) {emf.close();}
    }
    
}
