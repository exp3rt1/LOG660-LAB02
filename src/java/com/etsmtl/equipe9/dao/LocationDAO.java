package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Exemplaire;
import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.model.LocationId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

public class LocationDAO extends DAOAbstrait<Location, LocationId> {

    public List<Long> retournerFilmDejaLoue(String courriel){
        
        List<Long> liste = new ArrayList<>();
        
        try {
            connect();
             Query query = em.createQuery("SELECT e FROM Exemplaire e, Location l "
                     + "WHERE l.client.courriel = :courriel AND l.exemplaire.idexemplaire = "
                     + "e.idexemplaire").setParameter("courriel", courriel);
             List<Exemplaire> listeEx = query.getResultList();
             
             for (Exemplaire exemplaire : listeEx) {
                liste.add(exemplaire.getFilm().getIdfilm());
            }
             
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
        
        return liste;
    }
                          
    public boolean verifierLocationForfait(String courriel) {
        
        try {

            connect();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("P_TEST_LOCATION_FORFAIT");
            storedProcedure.registerStoredProcedureParameter("p_courrielClient", String.class, ParameterMode.IN);
            storedProcedure.setParameter("p_courrielClient", courriel);
           

            try {
                storedProcedure.execute();

            } catch (Exception e) {

                System.out.println("Le nombre maximum de location a ete atteint");
                return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }
    
    public boolean verifierLocationExemplaire(String courriel, Long idfilm) {
        
        try {

            connect();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("P_TEST_NBR_EXEMPLAIRE");
            storedProcedure.registerStoredProcedureParameter("p_courrielClient", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_idFilm", Long.class, ParameterMode.IN);
            storedProcedure.setParameter("p_courrielClient", courriel);
            storedProcedure.setParameter("p_idFilm", idfilm);

            try {
                storedProcedure.execute();

            // il ny a pu dexemplaire
            } catch (Exception e) {

                System.out.println("Il n'y a aucun exemplaire de libre pour ce film");
                return false;
            }

            // il reste un exemplaire
            return true;

        } catch (Exception e) {
            return false;
        } finally {
            disconnect();
        }
    }
    
    public boolean locationFilm(String courriel, Long idfilm) {

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
                return false;
            }

            // si tout cest bien passe
            System.out.println("Le film a ete louer");
            return true;

        } catch (Exception e) {
            return false;
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
