/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.GenreDAO;
import com.etsmtl.equipe9.dao.PaysDAO;
import com.etsmtl.equipe9.model.Genre;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Desktop
 */
public class GenreCtrl {
    
    GenreDAO dao = DAOFactory.getInstance().getGenreDAO();
    
    public List<String> getGenres (){
        
        List<String> listeNomGenre = new ArrayList<>();
        List<Genre> listeGenres = dao.findAll();
        
        for (Genre genre : listeGenres) {
            
            listeNomGenre.add(genre.getNom());
        }
        return listeNomGenre;
   }
    
    public static void main(String[] args) {
        
        GenreCtrl control = new GenreCtrl();
        List<String> liste = control.getGenres();
        
        for (String string : liste) {
            System.out.println(string);
        }
    }
}
