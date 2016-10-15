package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.model.LocationId;
import java.util.List;


public class LocationDAO extends DAOAbstrait<Location, LocationId>{

    
    @Override
    public List<Location> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Location findById(LocationId id) {
        try {
            connect();
            return this.em.find(Location.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }
    
    @Override
    public List<Location> findById(List<LocationId> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Location obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Location obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Location obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
