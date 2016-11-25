/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.model.MaVueMoyenne;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Nicolas Desktop
 */
public class MoyenneDAO extends DAOAbstrait<MaVueMoyenne, Long>{/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    @Override
    public List<MaVueMoyenne> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaVueMoyenne findById(Long id) {
        try {
            connect();
            return this.em.find(MaVueMoyenne.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public List<MaVueMoyenne> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(MaVueMoyenne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(MaVueMoyenne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(MaVueMoyenne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

}
