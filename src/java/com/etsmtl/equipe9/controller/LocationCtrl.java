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

    public void louerFilm(String courriel, Long idfilm) {

        dao.locationFilm(courriel, idfilm);

    }

    public static void main(String[] args) {
        LocationCtrl location = new LocationCtrl();
        location.louerFilm("MerryMBolton6@hotmail.com", 130623L);

    }
}
