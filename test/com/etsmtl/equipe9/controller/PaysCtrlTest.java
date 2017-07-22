package com.etsmtl.equipe9.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaysCtrlTest {
    
    public PaysCtrlTest() {
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
     * Test of getPays method, of class PaysCtrl.
     */
    @Test
    public void testGetPays() {
        System.out.println("getPays");
        PaysCtrl instance = new PaysCtrl();
        List<String> expResult = null;
        List<String> result = instance.getPays();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PaysCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PaysCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
