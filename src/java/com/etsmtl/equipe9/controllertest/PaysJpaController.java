
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author NicolasSevigny
 */
public class PaysJpaController implements Serializable {

    public PaysJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pays pays) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (pays.getFilms() == null) {
            pays.setFilms(new HashSet<Film>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Set<Film> attachedFilms = new HashSet<Film>();
            for (Film filmsFilmToAttach : pays.getFilms()) {
                filmsFilmToAttach = em.getReference(filmsFilmToAttach.getClass(), filmsFilmToAttach.getIdfilm());
                attachedFilms.add(filmsFilmToAttach);
            }
            pays.setFilms(attachedFilms);
            em.persist(pays);
            for (Film filmsFilm : pays.getFilms()) {
                filmsFilm.getPayses().add(pays);
                filmsFilm = em.merge(filmsFilm);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPays(pays.getIdpays()) != null) {
                throw new PreexistingEntityException("Pays " + pays + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pays pays) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pays persistentPays = em.find(Pays.class, pays.getIdpays());
            Set<Film> filmsOld = persistentPays.getFilms();
            Set<Film> filmsNew = pays.getFilms();
            Set<Film> attachedFilmsNew = new HashSet<Film>();
            for (Film filmsNewFilmToAttach : filmsNew) {
                filmsNewFilmToAttach = em.getReference(filmsNewFilmToAttach.getClass(), filmsNewFilmToAttach.getIdfilm());
                attachedFilmsNew.add(filmsNewFilmToAttach);
            }
            filmsNew = attachedFilmsNew;
            pays.setFilms(filmsNew);
            pays = em.merge(pays);
            for (Film filmsOldFilm : filmsOld) {
                if (!filmsNew.contains(filmsOldFilm)) {
                    filmsOldFilm.getPayses().remove(pays);
                    filmsOldFilm = em.merge(filmsOldFilm);
                }
            }
            for (Film filmsNewFilm : filmsNew) {
                if (!filmsOld.contains(filmsNewFilm)) {
                    filmsNewFilm.getPayses().add(pays);
                    filmsNewFilm = em.merge(filmsNewFilm);
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
                BigDecimal id = pays.getIdpays();
                if (findPays(id) == null) {
                    throw new NonexistentEntityException("The pays with id " + id + " no longer exists.");
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
            Pays pays;
            try {
                pays = em.getReference(Pays.class, id);
                pays.getIdpays();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pays with id " + id + " no longer exists.", enfe);
            }
            Set<Film> films = pays.getFilms();
            for (Film filmsFilm : films) {
                filmsFilm.getPayses().remove(pays);
                filmsFilm = em.merge(filmsFilm);
            }
            em.remove(pays);
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

    public List<Pays> findPaysEntities() {
        return findPaysEntities(true, -1, -1);
    }

    public List<Pays> findPaysEntities(int maxResults, int firstResult) {
        return findPaysEntities(false, maxResults, firstResult);
    }

    private List<Pays> findPaysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pays.class));
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

    public Pays findPays(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pays.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaysCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pays> rt = cq.from(Pays.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
