package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.PersonneDAO;
import com.etsmtl.equipe9.model.Personne;



public class PersonneCtrl {
    
   public Personne getPersonne (Long personne){
        
        PersonneDAO dao = new PersonneDAO();
        return dao.findById(personne);
    }
    
    public static void main(String[] args) {
        PersonneCtrl control = new PersonneCtrl();
        Personne pers = control.getPersonne(1848L);
        System.out.println("Le nom de la personne est: "+ pers.getNom());
        System.out.println("Sa bio est: "+ pers.getBiographie());
    }
}
