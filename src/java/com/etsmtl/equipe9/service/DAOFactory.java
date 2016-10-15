
package com.etsmtl.equipe9.service;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.dao.LocationDAO;
import com.etsmtl.equipe9.dao.PersonneDAO;


public final class DAOFactory {

    private static volatile DAOFactory instance = null;
    private FilmDAO filmDAO;
    private ClientDAO clientDAO;
    private PersonneDAO personneDAO;
    private LocationDAO locationDAO;

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
    
}

