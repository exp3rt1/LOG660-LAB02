package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dto.FilmDTO;
import com.etsmtl.equipe9.model.Film;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilmCtrlTest {
    
    private ArrayList<String> liste;
    
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
        liste = new ArrayList<String>();
        liste.add("shrek");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFilm method, of class FilmCtrl.
     */
    @Test
    public void testGetFilm() {
        FilmCtrl instance = new FilmCtrl();
        Film result = instance.getFilm(15864L);
        assertNotNull(result);
    }

    /**
     * Test of getFilms method, of class FilmCtrl.
     */
    @Test
    public void testGetFilms() {
        
        FilmDTO dto = new FilmDTO();
        dto.setTitles(liste);
        
        FilmCtrl instance = new FilmCtrl();
        List<Film> result = instance.getFilms(dto);
        
        System.out.println(result.size());
        assertNotNull(result);
    }

    /**
     * Test of getLangues method, of class FilmCtrl.
     */
    @Test
    public void testGetLangues() {
        FilmCtrl instance = new FilmCtrl();
        List<String> result = instance.getLangues();
        assertNotNull(result);
    }    
}
