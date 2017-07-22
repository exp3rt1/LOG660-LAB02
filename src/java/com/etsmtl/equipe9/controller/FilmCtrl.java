package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.dto.FilmDTO;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.List;

public class FilmCtrl {

    FilmDAO dao = DAOFactory.getInstance().getFilmDAO();

    public Film getFilm(Long idFilm) {
        return dao.findById(idFilm);
    }

    public List<Film> getFilms(FilmDTO dto) {
        return dao.rechercheFilm(dto);
    }

    public List<String> getLangues() {

        return dao.getLangues();

    }
}
