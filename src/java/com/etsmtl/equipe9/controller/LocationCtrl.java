package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

public class LocationCtrl {

    private LocationDAO dao = DAOFactory.getInstance().getLocationDAO();

    public boolean louerFilm(String courriel, Long idfilm) {

        return dao.locationFilm(courriel, idfilm);

    }
    
    public boolean verifierLocationExemplaire(String courriel, Long idfilm) {

        return dao.verifierLocationExemplaire(courriel, idfilm);

    }
    
    public boolean verifierLocationForfait(String courriel) {

        return dao.verifierLocationForfait(courriel);

    }

    public static void main(String[] args) {
        LocationCtrl location = new LocationCtrl();
        //location.verifierLocationExemplaire("MerryMBolton6@hotmail.com", 859163L);
        //location.louerFilm("MerryMBolton6@hotmail.com", 130623L);
        location.verifierLocationForfait("MerryMBolton6@hotmail.com");
        

    }
}
