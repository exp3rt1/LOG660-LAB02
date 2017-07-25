package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.service.DAOFactory;

public class LocationCtrl {

    private LocationDAO dao = DAOFactory.getInstance().getLocationDAO();

    // permet de faire la location d'un film
    public boolean louerFilm(String courriel, Long idfilm) {
        return dao.locationFilm(courriel, idfilm);
    }
    
    // permet de vérifier s'il y a un exemplaire de disponnible pour un film
    public boolean verifierLocationExemplaire(String courriel, Long idfilm) {
        return dao.verifierLocationExemplaire(courriel, idfilm);
    }
    
    // permet de vérifier si le client peut encore louer un film avec son type de forfait qu'il possède
    public boolean verifierLocationForfait(String courriel) {
        return dao.verifierLocationForfait(courriel);
    }

}
