
package com.etsmtl.equipe9.controllertest;

import com.etsmtl.equipe9.model.Exemplaire;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.test.exceptions.IllegalOrphanException;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author NicolasSevigny
 */
public class ExemplaireJpaController implements Serializable {

    public ExemplaireJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exemplaire exemplaire) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (exemplaire.getLocations() == null) {
            exemplaire.setLocations(new HashSet<Location>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Film film = exemplaire.getFilm();
            if (film != null) {
                film = em.getReference(film.getClass(), film.getIdfilm());
                exemplaire.setFilm(film);
            }
            Set<Location> attachedLocations = new HashSet<Location>();
            for (Location locationsLocationToAttach : exemplaire.getLocations()) {
                locationsLocationToAttach = em.getReference(locationsLocationToAttach.getClass(), locationsLocationToAttach.getId());
                attachedLocations.add(locationsLocationToAttach);
            }
            exemplaire.setLocations(attachedLocations);
            em.persist(exemplaire);
            if (film != null) {
                film.getExemplaires().add(exemplaire);
                film = em.merge(film);
            }
            for (Location locationsLocation : exemplaire.getLocations()) {
                Exemplaire oldExemplaireOfLocationsLocation = locationsLocation.getExemplaire();
                locationsLocation.setExemplaire(exemplaire);
                locationsLocation = em.merge(locationsLocation);
                if (oldExemplaireOfLocationsLocation != null) {
                    oldExemplaireOfLocationsLocation.getLocations().remove(locationsLocation);
                    oldExemplaireOfLocationsLocation = em.merge(oldExemplaireOfLocationsLocation);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findExemplaire(exemplaire.getIdexemplaire()) != null) {
                throw new PreexistingEntityException("Exemplaire " + exemplaire + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exemplaire exemplaire) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Exemplaire persistentExemplaire = em.find(Exemplaire.class, exemplaire.getIdexemplaire());
            Film filmOld = persistentExemplaire.getFilm();
            Film filmNew = exemplaire.getFilm();
            Set<Location> locationsOld = persistentExemplaire.getLocations();
            Set<Location> locationsNew = exemplaire.getLocations();
            List<String> illegalOrphanMessages = null;
            for (Location locationsOldLocation : locationsOld) {
                if (!locationsNew.contains(locationsOldLocation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Location " + locationsOldLocation + " since its exemplaire field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (filmNew != null) {
                filmNew = em.getReference(filmNew.getClass(), filmNew.getIdfilm());
                exemplaire.setFilm(filmNew);
            }
            Set<Location> attachedLocationsNew = new HashSet<Location>();
            for (Location locationsNewLocationToAttach : locationsNew) {
                locationsNewLocationToAttach = em.getReference(locationsNewLocationToAttach.getClass(), locationsNewLocationToAttach.getId());
                attachedLocationsNew.add(locationsNewLocationToAttach);
            }
            locationsNew = attachedLocationsNew;
            exemplaire.setLocations(locationsNew);
            exemplaire = em.merge(exemplaire);
            if (filmOld != null && !filmOld.equals(filmNew)) {
                filmOld.getExemplaires().remove(exemplaire);
                filmOld = em.merge(filmOld);
            }
            if (filmNew != null && !filmNew.equals(filmOld)) {
                filmNew.getExemplaires().add(exemplaire);
                filmNew = em.merge(filmNew);
            }
            for (Location locationsNewLocation : locationsNew) {
                if (!locationsOld.contains(locationsNewLocation)) {
                    Exemplaire oldExemplaireOfLocationsNewLocation = locationsNewLocation.getExemplaire();
                    locationsNewLocation.setExemplaire(exemplaire);
                    locationsNewLocation = em.merge(locationsNewLocation);
                    if (oldExemplaireOfLocationsNewLocation != null && !oldExemplaireOfLocationsNewLocation.equals(exemplaire)) {
                        oldExemplaireOfLocationsNewLocation.getLocations().remove(locationsNewLocation);
                        oldExemplaireOfLocationsNewLocation = em.merge(oldExemplaireOfLocationsNewLocation);
                    }
                }
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
                BigDecimal id = exemplaire.getIdexemplaire();
                if (findExemplaire(id) == null) {
                    throw new NonexistentEntityException("The exemplaire with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Exemplaire exemplaire;
            try {
                exemplaire = em.getReference(Exemplaire.class, id);
                exemplaire.getIdexemplaire();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exemplaire with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Location> locationsOrphanCheck = exemplaire.getLocations();
            for (Location locationsOrphanCheckLocation : locationsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exemplaire (" + exemplaire + ") cannot be destroyed since the Location " + locationsOrphanCheckLocation + " in its locations field has a non-nullable exemplaire field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Film film = exemplaire.getFilm();
            if (film != null) {
                film.getExemplaires().remove(exemplaire);
                film = em.merge(film);
            }
            em.remove(exemplaire);
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

    public List<Exemplaire> findExemplaireEntities() {
        return findExemplaireEntities(true, -1, -1);
    }

    public List<Exemplaire> findExemplaireEntities(int maxResults, int firstResult) {
        return findExemplaireEntities(false, maxResults, firstResult);
    }

    private List<Exemplaire> findExemplaireEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exemplaire.class));
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

    public Exemplaire findExemplaire(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exemplaire.class, id);
        } finally {
            em.close();
        }
    }

    public int getExemplaireCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exemplaire> rt = cq.from(Exemplaire.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
