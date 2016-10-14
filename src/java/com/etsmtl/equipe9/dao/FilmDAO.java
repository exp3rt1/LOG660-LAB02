package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Film;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class FilmDAO extends DAOAbstrait<Film, Long>{

    @Override
    public Film findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Film findById(Long id) {
        return this.emFind(Film.class, id);
    }
    
    @Override
    public List<Film> findById(List<Long> listeId) {
        
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select DISTINCT f from Film f where f.idfilm in :listeId").setParameter("listeId", listeId);
        List<Film> liste = query.getResultList();
        em.close();
        emf.close();
        
        return liste;
    }
    
    @Override
    public boolean insert(Film obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Film obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Film obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
