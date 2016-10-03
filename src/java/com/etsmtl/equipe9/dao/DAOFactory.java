package com.etsmtl.equipe9.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.etsmtl.equipe9.service.IDAO;

public final class DAOFactory {

    /*
    Serveur : big-data-3.logti.etsmtl.ca
    Port : 1521
    SID : Log660
     */
    private static final String username = "equipe9";
    private static final String password = "88lyZUIU";
    private static final String driver = "";

    private static volatile DAOFactory instance = null;

    private Connection conn = null;

    private DAOFactory() {
    }

    public final synchronized static DAOFactory getInstance() {
        if (DAOFactory.instance == null) {
            DAOFactory.instance = new DAOFactory();
        }
        return DAOFactory.instance;
    }

    public Connection getConn() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(driver, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.conn;
    }

    public IDAO getFilmDAO() {
        return new FilmDAO();
    }

    public IDAO getClientDAO() {
        return new ClientDAO();
    }
}
