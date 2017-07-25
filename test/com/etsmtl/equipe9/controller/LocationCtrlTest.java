package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationCtrlTest {
    
    public LocationCtrlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of louerFilm method, of class LocationCtrl.
     */
    @Test
    public void testLouerFilm() {
        System.out.println("louerFilm");
        String courriel = "MaggiePDepriest5@yahoo.com";
        // 42876
        Long idfilm = 42876L; // titre : Rashômon
        LocationCtrl instance = new LocationCtrl();
        
        boolean expResult = true;
        boolean result = instance.louerFilm(courriel, idfilm);
        assertEquals(expResult, result);
        
        // supprimer la location
        LocationDAO dao = DAOFactory.getInstance().getLocationDAO();
        dao.delete(dao.find(courriel, idfilm));
    }

    /**
     * Test of verifierLocationExemplaire method, of class LocationCtrl.
     */
    @Test
    public void testVerifierLocationExemplaire() {
        System.out.println("verifierLocationExemplaire");
        String courriel = "MaggiePDepriest5@yahoo.com";
        Long idfilm = 42876L; // titre : Rashômon
        LocationCtrl instance = new LocationCtrl();
        boolean expResult = true;
        boolean result = instance.verifierLocationExemplaire(courriel, idfilm);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifierLocationForfait method, of class LocationCtrl.
     */
    @Test
    public void testVerifierLocationForfait() {
        System.out.println("verifierLocationForfait");
        String courriel = "MaggiePDepriest5@yahoo.com";
        LocationCtrl instance = new LocationCtrl();
          
        boolean expResult = true;
        boolean result = instance.verifierLocationForfait(courriel);
        assertEquals(expResult, result);
        
        // MaggiePDepriest5@yahoo.com a un forfait avec 1 seul film à la fois
        // donc on fait une location et on vérifie si maintenant elle ne peut plus faire de location
        Long idfilm = 42876L; // titre : Rashômon
        instance.louerFilm(courriel, idfilm); // louer un film
        
        // vérifier si maintenant elle ne peut plus faire de location de film
        expResult = false;
        result = instance.verifierLocationForfait(courriel);
        assertEquals(expResult, result);
        
        // supprimer la location
        LocationDAO dao = DAOFactory.getInstance().getLocationDAO();
        dao.delete(dao.find(courriel, idfilm));
    }
    
}
