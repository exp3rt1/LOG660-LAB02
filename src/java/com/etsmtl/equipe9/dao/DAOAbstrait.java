
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.service.Configuration;
import com.etsmtl.equipe9.service.IDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DAOAbstrait<T,PK> implements IDAO<T,PK> {
    
    public EntityManagerFactory emf;
    public EntityManager em;
    
    public void connect(){
        this.emf = Persistence.createEntityManagerFactory(Configuration.PERSISTANCE_UNIT_NAME);
        this.em = emf.createEntityManager();
    }
    
    public void disconnect(){
        if (em.isOpen()) { em.close();}
        if (emf.isOpen()) {emf.close();}
    }
    
}
