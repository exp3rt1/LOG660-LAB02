/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.PaysDAO;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Desktop
 */
public class PaysCtrl {
    
    PaysDAO dao = DAOFactory.getInstance().getPaysDAO();
    
    public List<String> getPays (){
        
        List<String> listeNomPays = new ArrayList<>();
        List<Pays> listePays = dao.findAll();
        
        for (Pays pays : listePays) {
            
            listeNomPays.add(pays.getNom());
        }
        return listeNomPays;
   }
    
    public static void main(String[] args) {
        
        PaysCtrl control = new PaysCtrl();
        List<String> liste = control.getPays();
        
        for (String string : liste) {
            System.out.println(string);
        }
    }
}
