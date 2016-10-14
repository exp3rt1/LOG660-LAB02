
package com.etsmtl.equipe9.service;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.dao.FilmDAO;


public final class DAOFactory {

    private static volatile DAOFactory instance = null;

    private DAOFactory() {
    }

    public final synchronized static DAOFactory getInstance() {
        if (DAOFactory.instance == null) {
            DAOFactory.instance = new DAOFactory();
        }
        return DAOFactory.instance;
    }
    
    public IDAO getFilmDAO() {
        return new FilmDAO();
    }
    
    public IDAO getClientDAO() {
        return new ClientDAO();
    }
}

