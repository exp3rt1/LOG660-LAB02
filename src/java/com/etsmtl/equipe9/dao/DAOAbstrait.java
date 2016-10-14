
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.service.IDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DAOAbstrait<T,PK> implements IDAO<T,PK> {
    
    public final String PERSISTANCE_UNIT_NAME = "WebApplication1PU";
    public EntityManagerFactory emf;
    public EntityManager em;
    
    public void connect() throws Exception{
        this.emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
        this.em = emf.createEntityManager();
    }
    
    public void disconnect(){
        em.close();
        emf.close();
    }
    
    public <T> T emFind(Class<T> entityClass, Object primaryKey){
        try {
            connect();
            return this.em.find(entityClass, primaryKey);
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }
    
}
