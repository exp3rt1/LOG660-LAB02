package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.GenreDAO;
import com.etsmtl.equipe9.model.Genre;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.ArrayList;
import java.util.List;

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
}
