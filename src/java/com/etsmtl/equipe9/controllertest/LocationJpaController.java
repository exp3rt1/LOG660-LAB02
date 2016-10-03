
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.model.Exemplaire;
import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.model.LocationId;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author NicolasSevigny
 */
public class LocationJpaController implements Serializable {

    public LocationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Location location) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (location.getId() == null) {
            location.setId(new LocationId());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client client = location.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getCourriel());
                location.setClient(client);
            }
            Exemplaire exemplaire = location.getExemplaire();
            if (exemplaire != null) {
                exemplaire = em.getReference(exemplaire.getClass(), exemplaire.getIdexemplaire());
                location.setExemplaire(exemplaire);
            }
            em.persist(location);
            if (client != null) {
                client.getLocations().add(location);
                client = em.merge(client);
            }
            if (exemplaire != null) {
                exemplaire.getLocations().add(location);
                exemplaire = em.merge(exemplaire);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findLocation(location.getId()) != null) {
                throw new PreexistingEntityException("Location " + location + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Location location) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Location persistentLocation = em.find(Location.class, location.getId());
            Client clientOld = persistentLocation.getClient();
            Client clientNew = location.getClient();
            Exemplaire exemplaireOld = persistentLocation.getExemplaire();
            Exemplaire exemplaireNew = location.getExemplaire();
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getCourriel());
                location.setClient(clientNew);
            }
            if (exemplaireNew != null) {
                exemplaireNew = em.getReference(exemplaireNew.getClass(), exemplaireNew.getIdexemplaire());
                location.setExemplaire(exemplaireNew);
            }
            location = em.merge(location);
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getLocations().remove(location);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getLocations().add(location);
                clientNew = em.merge(clientNew);
            }
            if (exemplaireOld != null && !exemplaireOld.equals(exemplaireNew)) {
                exemplaireOld.getLocations().remove(location);
                exemplaireOld = em.merge(exemplaireOld);
            }
            if (exemplaireNew != null && !exemplaireNew.equals(exemplaireOld)) {
                exemplaireNew.getLocations().add(location);
                exemplaireNew = em.merge(exemplaireNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                LocationId id = location.getId();
                if (findLocation(id) == null) {
                    throw new NonexistentEntityException("The location with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(LocationId id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Location location;
            try {
                location = em.getReference(Location.class, id);
                location.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The location with id " + id + " no longer exists.", enfe);
            }
            Client client = location.getClient();
            if (client != null) {
                client.getLocations().remove(location);
                client = em.merge(client);
            }
            Exemplaire exemplaire = location.getExemplaire();
            if (exemplaire != null) {
                exemplaire.getLocations().remove(location);
                exemplaire = em.merge(exemplaire);
            }
            em.remove(location);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Location> findLocationEntities() {
        return findLocationEntities(true, -1, -1);
    }

    public List<Location> findLocationEntities(int maxResults, int firstResult) {
        return findLocationEntities(false, maxResults, firstResult);
    }

    private List<Location> findLocationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Location.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Location findLocation(LocationId id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Location.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Location> rt = cq.from(Location.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
