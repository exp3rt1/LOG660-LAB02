package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.model.Personne;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonneCtrlTest {
    
    public PersonneCtrlTest() {
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
     * Test of getPersonne method, of class PersonneCtrl.
     */
    @Test
    public void testGetPersonne() {
        System.out.println("getPersonne");
        Long personne = null;
        PersonneCtrl instance = new PersonneCtrl();
        Personne expResult = null;
        Personne result = instance.getPersonne(personne);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PersonneCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PersonneCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
