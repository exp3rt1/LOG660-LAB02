/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.service.IDAO;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nicolas Desktop
 */
public class PaysDAO extends DAOAbstrait<Pays, Long>{/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    @Override
    public List<Pays> findAll() {
       
        try {
            connect();
             Query query = em.createQuery("SELECT p FROM Pays p ORDER BY p.nom asc");
             List<Pays> listePays = query.getResultList();
             return listePays;
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public Pays findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pays> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Pays obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Pays obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Pays obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
