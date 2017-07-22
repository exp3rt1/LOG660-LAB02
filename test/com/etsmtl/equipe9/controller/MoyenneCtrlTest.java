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
        Long idFilm = 119190L;
        MoyenneCtrl instance = new MoyenneCtrl();
        BigDecimal result = instance.getMoyenne(idFilm);
        assertNotNull(result);
    }
    
}
