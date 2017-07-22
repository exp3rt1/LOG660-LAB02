package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.PaysDAO;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.ArrayList;
import java.util.List;

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

}
