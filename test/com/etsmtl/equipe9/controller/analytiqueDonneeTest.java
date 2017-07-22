package com.etsmtl.equipe9.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class analytiqueDonneeTest {
    
    public analytiqueDonneeTest() {
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
     * Test of main method, of class analytiqueDonnee.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        analytiqueDonnee.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNbLocations method, of class analytiqueDonnee.
     */
    @Test
    public void testGetNbLocations() {
        System.out.println("getNbLocations");
        String query = "";
        int expResult = 0;
        int result = analytiqueDonnee.getNbLocations(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
