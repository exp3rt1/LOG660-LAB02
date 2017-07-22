package com.etsmtl.equipe9.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenreCtrlTest {
    
    public GenreCtrlTest() {
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
     * Test of getGenres method, of class GenreCtrl.
     */
    @Test
    public void testGetGenres() {
        System.out.println("getGenres");
        GenreCtrl instance = new GenreCtrl();
        List<String> expResult = null;
        List<String> result = instance.getGenres();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class GenreCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GenreCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
