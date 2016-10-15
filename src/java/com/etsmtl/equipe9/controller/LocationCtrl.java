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

    public void locationFilm(String courriel, Long idfilm) {

        // Entity Manager
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("P_LOCATIONFILM");
        storedProcedure.registerStoredProcedureParameter("p_courrielClient", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_idFilm", Long.class, ParameterMode.IN);
        storedProcedure.setParameter("p_courrielClient", courriel);
        storedProcedure.setParameter("p_idFilm", idfilm);
 
        try {
            storedProcedure.execute();
        } catch (Exception e) {

            
            
            //return e.;
            
        }
        
        //return "Le film a ete loue";
       

    }
    
    public static void main(String[] args) {
        LocationCtrl location = new LocationCtrl();
        location.locationFilm("MerryMBolton6@hotmail.com", 130623L);
        
        
    }
}
