package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.model.Film;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;



public class LocationCtrl {

    public void locationFilm(String courriel, Long idfilm) throws SQLException{

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
        } catch (SQLException  e) {
            
            
            
            //return e.;
            
        }
        
        //return "Le film a ete loue";
       

    }
    
    public static void main(String[] args) {
        LocationCtrl location = new LocationCtrl();
        location.locationFilm("MerryMBolton6@hotmail.com", 130623L);
        
        
    }
}
