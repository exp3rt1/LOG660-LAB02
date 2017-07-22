package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Genre;
import java.util.List;
import javax.persistence.Query;

public class GenreDAO extends DAOAbstrait<Genre, Long>{

    @Override
    public List<Genre> findAll() {
       
        try {
            connect();
             Query query = em.createQuery("SELECT g FROM Genre g ORDER BY g.nom asc");
             List<Genre> listeGenre = query.getResultList();
             return listeGenre;
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public Genre findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Genre> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(Genre obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Genre obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Genre obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
