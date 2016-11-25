/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.MoyenneDAO;
import com.etsmtl.equipe9.dao.PaysDAO;
import com.etsmtl.equipe9.model.MaVueMoyenne;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.service.DAOFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Desktop
 */
public class MoyenneCtrl {
    
    MoyenneDAO dao = DAOFactory.getInstance().getMoyenneDAO();
    
    public BigDecimal getMoyenne (BigDecimal idFilm){
        
        
        MaVueMoyenne moyenne = dao.findById(idFilm);
        
        return moyenne.getCote();
   }
    
    public static void main(String[] args) {
        
        MoyenneCtrl control = new MoyenneCtrl();
        
        System.out.println(control.getMoyenne(new BigDecimal("119190")));
        
    }
}
