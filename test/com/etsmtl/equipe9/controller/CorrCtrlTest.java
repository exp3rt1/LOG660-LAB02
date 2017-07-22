package com.etsmtl.equipe9.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CorrCtrlTest {
    
    public CorrCtrlTest() {
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
     * Test of getCorr method, of class CorrCtrl.
     */
    @Test
    public void testGetCorr() {
        System.out.println("getCorr");
        Long idFilm = null;
        String courriel = "";
        CorrCtrl instance = new CorrCtrl();
        List<Long> expResult = null;
        List<Long> result = instance.getCorr(idFilm, courriel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CorrCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CorrCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
