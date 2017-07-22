package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.PersonneDAO;
import com.etsmtl.equipe9.model.Personne;
import com.etsmtl.equipe9.service.DAOFactory;



public class PersonneCtrl {
    
    PersonneDAO dao = DAOFactory.getInstance().getPersonneDAO();
    
   public Personne getPersonne (Long personne){
        return dao.findById(personne);
    }
   
}
