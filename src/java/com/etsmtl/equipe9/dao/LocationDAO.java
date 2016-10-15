package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.model.LocationId;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class LocationDAO extends DAOAbstrait<Location, LocationId> {

    public void locationFilm(String courriel, Long idfilm) {

        try {

            connect();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("P_LOCATIONFILM");
            storedProcedure.registerStoredProcedureParameter("p_courrielClient", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_idFilm", Long.class, ParameterMode.IN);
            storedProcedure.setParameter("p_courrielClient", courriel);
            storedProcedure.setParameter("p_idFilm", idfilm);

            try {
                storedProcedure.execute();

            // dans le cas ou on ne peut pas louer
            } catch (Exception e) {

                System.out.println("Impossible de louer le film");
                return;
            }

            // si tout cest bien passe
            System.out.println("Le film a ete louer");

        } catch (Exception e) {
            return;
        } finally {
            disconnect();
        }
    }

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
