package com.etsmtl.equipe9.controller;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoyenneCtrlTest {
    
    public MoyenneCtrlTest() {
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
     * Test of getMoyenne method, of class MoyenneCtrl.
     */
    @Test
    public void testGetMoyenne() {
        System.out.println("getMoyenne");
        Long idFilm = null;
        MoyenneCtrl instance = new MoyenneCtrl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getMoyenne(idFilm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class MoyenneCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MoyenneCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
