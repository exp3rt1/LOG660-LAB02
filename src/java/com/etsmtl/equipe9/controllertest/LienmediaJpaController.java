
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Lienmedia;
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
public class LienmediaJpaController implements Serializable {

    public LienmediaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lienmedia lienmedia) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Film film = lienmedia.getFilm();
            if (film != null) {
                film = em.getReference(film.getClass(), film.getIdfilm());
                lienmedia.setFilm(film);
            }
            em.persist(lienmedia);
            if (film != null) {
                film.getLienmedias().add(lienmedia);
                film = em.merge(film);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findLienmedia(lienmedia.getIdlienmedia()) != null) {
                throw new PreexistingEntityException("Lienmedia " + lienmedia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lienmedia lienmedia) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lienmedia persistentLienmedia = em.find(Lienmedia.class, lienmedia.getIdlienmedia());
            Film filmOld = persistentLienmedia.getFilm();
            Film filmNew = lienmedia.getFilm();
            if (filmNew != null) {
                filmNew = em.getReference(filmNew.getClass(), filmNew.getIdfilm());
                lienmedia.setFilm(filmNew);
            }
            lienmedia = em.merge(lienmedia);
            if (filmOld != null && !filmOld.equals(filmNew)) {
                filmOld.getLienmedias().remove(lienmedia);
                filmOld = em.merge(filmOld);
            }
            if (filmNew != null && !filmNew.equals(filmOld)) {
                filmNew.getLienmedias().add(lienmedia);
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
                BigDecimal id = lienmedia.getIdlienmedia();
                if (findLienmedia(id) == null) {
                    throw new NonexistentEntityException("The lienmedia with id " + id + " no longer exists.");
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
            Lienmedia lienmedia;
            try {
                lienmedia = em.getReference(Lienmedia.class, id);
                lienmedia.getIdlienmedia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lienmedia with id " + id + " no longer exists.", enfe);
            }
            Film film = lienmedia.getFilm();
            if (film != null) {
                film.getLienmedias().remove(lienmedia);
                film = em.merge(film);
            }
            em.remove(lienmedia);
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

    public List<Lienmedia> findLienmediaEntities() {
        return findLienmediaEntities(true, -1, -1);
    }

    public List<Lienmedia> findLienmediaEntities(int maxResults, int firstResult) {
        return findLienmediaEntities(false, maxResults, firstResult);
    }

    private List<Lienmedia> findLienmediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lienmedia.class));
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

    public Lienmedia findLienmedia(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lienmedia.class, id);
        } finally {
            em.close();
        }
    }

    public int getLienmediaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lienmedia> rt = cq.from(Lienmedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
