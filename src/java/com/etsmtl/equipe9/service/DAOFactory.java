
package com.etsmtl.equipe9.service;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.dao.CorrDAO;
import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.dao.GenreDAO;
import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.dao.MoyenneDAO;
import com.etsmtl.equipe9.dao.PaysDAO;
import com.etsmtl.equipe9.dao.PersonneDAO;


public final class DAOFactory {

    private static volatile DAOFactory instance = null;
    private FilmDAO filmDAO;
    private ClientDAO clientDAO;
    private PersonneDAO personneDAO;
    private LocationDAO locationDAO;
    private PaysDAO paysDAO;
    private GenreDAO genreDAO;
    private MoyenneDAO moyenneDAO;
    private CorrDAO corrDAO;

    private DAOFactory() {}

    public final synchronized static DAOFactory getInstance() {
        if (DAOFactory.instance == null) {
            DAOFactory.instance = new DAOFactory();
        }
        return DAOFactory.instance;
    }
    
    public FilmDAO getFilmDAO() {
        if (filmDAO == null) {
            filmDAO = new FilmDAO();
        }
        return filmDAO;
    }
    
    public ClientDAO getClientDAO() {
        if (clientDAO == null) {
            clientDAO = new ClientDAO();
        }
        return clientDAO;
    }
    
    public PersonneDAO getPersonneDAO() {
        if (personneDAO == null) {
            personneDAO = new PersonneDAO();
        }
        return personneDAO;
    }
    
    public LocationDAO getLocationDAO() {
        if (locationDAO == null) {
            locationDAO = new LocationDAO();
        }
        return locationDAO;
    }
    
    public PaysDAO getPaysDAO() {
        if (paysDAO == null) {
            paysDAO = new PaysDAO();
        }
        return paysDAO;
    }
    
    public GenreDAO getGenreDAO() {
        if (genreDAO == null) {
            genreDAO = new GenreDAO();
        }
        return genreDAO;
    }
    
    public MoyenneDAO getMoyenneDAO() {
        if (moyenneDAO == null) {
            moyenneDAO = new MoyenneDAO();
        }
        return moyenneDAO;
    }
    
    public CorrDAO getCorrDAO() {
        if (corrDAO == null) {
            corrDAO = new CorrDAO();
        }
        return corrDAO;
    }
    
}

