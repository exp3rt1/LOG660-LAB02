package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Personne;



public class FilmCtrl {
    
   public Film getFilm (Long idFilm){
        
        FilmDAO dao = new FilmDAO();
        return dao.findById(idFilm);
    }
    
    public static void main(String[] args) {
        FilmCtrl control = new FilmCtrl();
        Film film = control.getFilm(130623L);
        System.out.println("Le titre du film est: "+ film.getTitre());
        System.out.println("Sa langue originale est: "+ film.getLangueoriginale());
    }
}
