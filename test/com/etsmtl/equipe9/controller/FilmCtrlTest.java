package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dto.FilmDTO;
import com.etsmtl.equipe9.model.Film;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilmCtrlTest {
    
    public FilmCtrlTest() {
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
     * Test of getFilm method, of class FilmCtrl.
     */
    @Test
    public void testGetFilm() {
        System.out.println("getFilm");
        Long idFilm = null;
        FilmCtrl instance = new FilmCtrl();
        Film expResult = null;
        Film result = instance.getFilm(idFilm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilms method, of class FilmCtrl.
     */
    @Test
    public void testGetFilms() {
        System.out.println("getFilms");
        FilmDTO dto = null;
        FilmCtrl instance = new FilmCtrl();
        List<Film> expResult = null;
        List<Film> result = instance.getFilms(dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLangues method, of class FilmCtrl.
     */
    @Test
    public void testGetLangues() {
        System.out.println("getLangues");
        FilmCtrl instance = new FilmCtrl();
        List<String> expResult = null;
        List<String> result = instance.getLangues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FilmCtrl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FilmCtrl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
