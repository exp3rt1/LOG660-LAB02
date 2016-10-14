package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.PersonneDAO;
import com.etsmtl.equipe9.model.Personne;
import com.etsmtl.equipe9.service.DAOFactory;



public class PersonneCtrl {
    
    PersonneDAO dao = DAOFactory.getInstance().getPersonneDAO();
    
   public Personne getPersonne (Long personne){
        return dao.findById(personne);
    }
    
    public static void main(String[] args) {
        PersonneCtrl control = new PersonneCtrl();
        Personne pers = control.getPersonne(1848L);
        System.out.println("Le nom de la personne est: "+ pers.getNom());
        System.out.println("Sa bio est: "+ pers.getBiographie());
    }
}
