package com.etsmtl.equipe9.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDAO extends DAO {

    @Override
    public Object findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Connection conn = null;
        try {
            PreparedStatement ps = conn.prepareCall("select * from client");
            ResultSet r = ps.executeQuery();
            r.next();
            System.out.println("GG "+ r.getString("courriel"));
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

}
