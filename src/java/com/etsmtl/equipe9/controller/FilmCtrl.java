package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Personne;
import java.util.ArrayList;
import java.util.List;



public class FilmCtrl {
    
   public Film getFilm (Long idFilm){
        
        FilmDAO dao = new FilmDAO();
        return dao.findById(idFilm);
   }
   
   public List<Film> getFilms (List<Long> idFilm){
        
        FilmDAO dao = new FilmDAO();
        return dao.findById(idFilm);
   }
   
   public void rechercheFilm (Long idFilm){
       
   }
    
    public static void main(String[] args) {
        FilmCtrl control = new FilmCtrl();
        Film film = control.getFilm(130623L);
        System.out.println("Le titre du film est: " + film.getTitre());
        System.out.println("Sa langue originale est: " + film.getLangueoriginale());

        List<Long> list = new ArrayList<Long>();
        list.add(130623L);
        list.add(92644L);
        list.add(1190080L);

        List<Film> films = control.getFilms(list);
        
        for (Film film1 : films) {
            
            System.out.println(film1.getTitre()+" "+film1.getAnneesortie());
        }
    }
}
