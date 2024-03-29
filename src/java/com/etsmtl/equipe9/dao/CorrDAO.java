/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.MaVueCorrelation;
import com.etsmtl.equipe9.model.Pays;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Nicolas Desktop
 */
public class CorrDAO extends DAOAbstrait<MaVueCorrelation, Long>{/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    public List<Long> findByIdAll(Long id) {
        
        List<Long> liste = new ArrayList<>();
        
        try {
            connect();
             Query query = em.createQuery("SELECT m FROM MaVueCorrelation"
                     + " m WHERE m.maVueCorrelationPK.idfilm2 = :idfilm2 ORDER BY "
                     + "m.correlation DESC").setParameter("idfilm2", id);
             List<MaVueCorrelation> listeCorr = query.getResultList();
             
            for (MaVueCorrelation maVueCorrelation : listeCorr) {
                
                liste.add(maVueCorrelation.getMaVueCorrelationPK().getIdfilm1());
            }
             
             return liste;
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }
    
    @Override
    public List<MaVueCorrelation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaVueCorrelation findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MaVueCorrelation> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(MaVueCorrelation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(MaVueCorrelation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(MaVueCorrelation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

   

}
