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
        CorrCtrl instance = new CorrCtrl();
        
        List<Long> listefilm = instance.getCorr(61184L, "MichaelEWash74@gmail.com");
        assertEquals(listefilm.size(), 3);
    }
}
