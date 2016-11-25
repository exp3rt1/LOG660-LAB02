/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.CorrDAO;
import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.model.MaVueCorrelation;
import com.etsmtl.equipe9.service.DAOFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author Nicolas Desktop
 */
public class CorrCtrl {
    
    CorrDAO daoC = DAOFactory.getInstance().getCorrDAO();
    LocationDAO daoL = DAOFactory.getInstance().getLocationDAO();
    
    public ArrayList<Long> getCorr(Long idFilm){
        
        
        List<MaVueCorrelation> listeCorr = daoC.findByIdAll(idFilm);
        ArrayList<Long> listefilm = new ArrayList<>();
        int i = 0;
        for (MaVueCorrelation corr : listeCorr) {
            
            if (i == 3) {
                break;
            }
            listefilm.add(corr.getMaVueCorrelationPK().getIdfilm1());
            i++;
        }
        
        return listefilm;
    }
    
    public static void main(String[] args) {
        
        CorrCtrl control = new CorrCtrl();
        
        ArrayList<Long> listefilm = control.getCorr(61184L);
        
        for (Long long1 : listefilm) {
            
            System.out.println(long1);
        }
        
        
    }
}
