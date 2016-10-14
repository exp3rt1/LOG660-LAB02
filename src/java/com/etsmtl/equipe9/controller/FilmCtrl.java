package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.dto.FilmDTO;
import com.etsmtl.equipe9.dto.YearInterval;
import com.etsmtl.equipe9.model.Film;
import java.util.ArrayList;
import java.util.List;



public class FilmCtrl {
    
   public Film getFilm (Long idFilm){
        
        FilmDAO dao = new FilmDAO();
        return dao.findById(idFilm);
   }
   
   public List<Film> getFilms (FilmDTO dto){
        
        FilmDAO dao = new FilmDAO();
        return dao.rechercheFilm(dto);
   }
   
    public static void main(String[] args) {
        
        ArrayList<String> listetitre = new ArrayList<>();
        //listetitre.add("the");
        //listetitre.add("at");
       
        
        ArrayList<String> listelangue = new ArrayList<>();
        //listelangue.add("Portuguese");
        //listelangue.add("English");
        
        ArrayList<String> listepays = new ArrayList<>();
        //listepays.add("uk");
        //listepays.add("USA");
        
        ArrayList<String> listegenre = new ArrayList<>();
        //listegenre.add("Comedy");
        //listegenre.add("action");
        
        ArrayList<String> listerealisateur = new ArrayList<>();
        //listerealisateur.add("tim");
        //listerealisateur.add("Tim B");
        
        ArrayList<String> listeacteur = new ArrayList<>();
        //listeacteur.add("jack");
        //listeacteur.add("Anna");
        
        ArrayList<Integer> listeanneesortie = new ArrayList<>();
        //listeanneesortie.add(1992);
        //listeanneesortie.add(1989);
        
        ArrayList<YearInterval> listeanneeinterval = new ArrayList<>();
        //listeanneeinterval.add(new YearInterval(2000, 2005));
        //listeanneeinterval.add(new YearInterval(0, 2000));
        
        FilmDTO dto = new FilmDTO();
       
        dto.setTitles(listetitre);
        dto.setOriginalLanguages(listelangue);
        dto.setCountries(listepays);
        dto.setGenres(listegenre);
        dto.setDirectors(listerealisateur);
        dto.setActors(listeacteur);
        dto.setReleaseDates(listeanneesortie);
        dto.setDateIntervals(listeanneeinterval);
        
        FilmCtrl control = new FilmCtrl();
        List<Film> films = control.getFilms(dto);
        
        for (Film film : films) {
            
            System.out.println(film.getTitre()+" "+film.getAnneesortie());
        }
        
        
        
        
    }
}
