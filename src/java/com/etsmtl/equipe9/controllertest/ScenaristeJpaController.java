
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Scenariste;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author NicolasSevigny
 */
public class ScenaristeJpaController implements Serializable {

    public ScenaristeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Scenariste scenariste) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Film film = scenariste.getFilm();
            if (film != null) {
                film = em.getReference(film.getClass(), film.getIdfilm());
                scenariste.setFilm(film);
            }
            em.persist(scenariste);
            if (film != null) {
                film.getScenaristes().add(scenariste);
                film = em.merge(film);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findScenariste(scenariste.getIdscenariste()) != null) {
                throw new PreexistingEntityException("Scenariste " + scenariste + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Scenariste scenariste) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Scenariste persistentScenariste = em.find(Scenariste.class, scenariste.getIdscenariste());
            Film filmOld = persistentScenariste.getFilm();
            Film filmNew = scenariste.getFilm();
            if (filmNew != null) {
                filmNew = em.getReference(filmNew.getClass(), filmNew.getIdfilm());
                scenariste.setFilm(filmNew);
            }
            scenariste = em.merge(scenariste);
            if (filmOld != null && !filmOld.equals(filmNew)) {
                filmOld.getScenaristes().remove(scenariste);
                filmOld = em.merge(filmOld);
            }
            if (filmNew != null && !filmNew.equals(filmOld)) {
                filmNew.getScenaristes().add(scenariste);
                filmNew = em.merge(filmNew);
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
                BigDecimal id = scenariste.getIdscenariste();
                if (findScenariste(id) == null) {
                    throw new NonexistentEntityException("The scenariste with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Scenariste scenariste;
            try {
                scenariste = em.getReference(Scenariste.class, id);
                scenariste.getIdscenariste();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scenariste with id " + id + " no longer exists.", enfe);
            }
            Film film = scenariste.getFilm();
            if (film != null) {
                film.getScenaristes().remove(scenariste);
                film = em.merge(film);
            }
            em.remove(scenariste);
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

    public List<Scenariste> findScenaristeEntities() {
        return findScenaristeEntities(true, -1, -1);
    }

    public List<Scenariste> findScenaristeEntities(int maxResults, int firstResult) {
        return findScenaristeEntities(false, maxResults, firstResult);
    }

    private List<Scenariste> findScenaristeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Scenariste.class));
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

    public Scenariste findScenariste(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Scenariste.class, id);
        } finally {
            em.close();
        }
    }

    public int getScenaristeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Scenariste> rt = cq.from(Scenariste.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
