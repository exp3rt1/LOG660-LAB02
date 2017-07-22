package com.etsmtl.equipe9.controller;

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
        String courriel = "";
        Long idfilm = null;
        LocationCtrl instance = new LocationCtrl();
        boolean expResult = false;
        boolean result = instance.louerFilm(courriel, idfilm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifierLocationExemplaire method, of class LocationCtrl.
     */
    @Test
    public void testVerifierLocationExemplaire() {
        System.out.println("verifierLocationExemplaire");
        String courriel = "";
        Long idfilm = null;
        LocationCtrl instance = new LocationCtrl();
        boolean expResult = false;
        boolean result = instance.verifierLocationExemplaire(courriel, idfilm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifierLocationForfait method, of class LocationCtrl.
     */
    @Test
    public void testVerifierLocationForfait() {
        System.out.println("verifierLocationForfait");
        String courriel = "";
        LocationCtrl instance = new LocationCtrl();
        boolean expResult = false;
        boolean result = instance.verifierLocationForfait(courriel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class LocationCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LocationCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
