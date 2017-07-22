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
        GenreCtrl instance = new GenreCtrl();
        List<String> result = instance.getGenres();
        assertNotNull(result);
    }
    
}
