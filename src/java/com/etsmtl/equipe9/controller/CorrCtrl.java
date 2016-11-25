/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.CorrDAO;
import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Desktop
 */
public class CorrCtrl {
    
    CorrDAO daoC = DAOFactory.getInstance().getCorrDAO();
    LocationDAO daoL = DAOFactory.getInstance().getLocationDAO();
    
    public List<Long> getCorr(Long idFilm, String courriel){
        
        List<Long> listeCorrTrier = daoC.findByIdAll(idFilm);
        
        List<Long>listeFilmDejaLoue = daoL.retournerFilmDejaLoue(courriel);
        
        listeCorrTrier.removeAll(listeFilmDejaLoue);
        
        
        int i = 0;
        List<Long> listeRecommandation = new ArrayList<>();
        
        for (Long long1 : listeCorrTrier) {
            
            if (i == 3) {
                break;
            }
            listeRecommandation.add(long1);
            i++;
        }
        
        
        return listeRecommandation;
    }
    
    public static void main(String[] args) {
        
        CorrCtrl control = new CorrCtrl();
        
        List<Long> listefilm = control.getCorr(61184L, "MichaelEWash74@gmail.com");
        
        for (Long long1 : listefilm) {
            
            System.out.println(long1);
        }
        
        
    }
}
