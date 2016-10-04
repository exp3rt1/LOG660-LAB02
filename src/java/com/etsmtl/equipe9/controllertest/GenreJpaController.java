
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Genre;
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
public class GenreJpaController implements Serializable {

    public GenreJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genre genre) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (genre.getFilms() == null) {
            genre.setFilms(new HashSet<Film>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Set<Film> attachedFilms = new HashSet<Film>();
            for (Film filmsFilmToAttach : genre.getFilms()) {
                filmsFilmToAttach = em.getReference(filmsFilmToAttach.getClass(), filmsFilmToAttach.getIdfilm());
                attachedFilms.add(filmsFilmToAttach);
            }
            genre.setFilms(attachedFilms);
            em.persist(genre);
            for (Film filmsFilm : genre.getFilms()) {
                filmsFilm.getGenres().add(genre);
                filmsFilm = em.merge(filmsFilm);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGenre(genre.getIdgenre()) != null) {
                throw new PreexistingEntityException("Genre " + genre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genre genre) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Genre persistentGenre = em.find(Genre.class, genre.getIdgenre());
            Set<Film> filmsOld = persistentGenre.getFilms();
            Set<Film> filmsNew = genre.getFilms();
            Set<Film> attachedFilmsNew = new HashSet<Film>();
            for (Film filmsNewFilmToAttach : filmsNew) {
                filmsNewFilmToAttach = em.getReference(filmsNewFilmToAttach.getClass(), filmsNewFilmToAttach.getIdfilm());
                attachedFilmsNew.add(filmsNewFilmToAttach);
            }
            filmsNew = attachedFilmsNew;
            genre.setFilms(filmsNew);
            genre = em.merge(genre);
            for (Film filmsOldFilm : filmsOld) {
                if (!filmsNew.contains(filmsOldFilm)) {
                    filmsOldFilm.getGenres().remove(genre);
                    filmsOldFilm = em.merge(filmsOldFilm);
                }
            }
            for (Film filmsNewFilm : filmsNew) {
                if (!filmsOld.contains(filmsNewFilm)) {
                    filmsNewFilm.getGenres().add(genre);
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
                BigDecimal id = genre.getIdgenre();
                if (findGenre(id) == null) {
                    throw new NonexistentEntityException("The genre with id " + id + " no longer exists.");
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
            Genre genre;
            try {
                genre = em.getReference(Genre.class, id);
                genre.getIdgenre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genre with id " + id + " no longer exists.", enfe);
            }
            Set<Film> films = genre.getFilms();
            for (Film filmsFilm : films) {
                filmsFilm.getGenres().remove(genre);
                filmsFilm = em.merge(filmsFilm);
            }
            em.remove(genre);
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

    public List<Genre> findGenreEntities() {
        return findGenreEntities(true, -1, -1);
    }

    public List<Genre> findGenreEntities(int maxResults, int firstResult) {
        return findGenreEntities(false, maxResults, firstResult);
    }

    private List<Genre> findGenreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genre.class));
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

    public Genre findGenre(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genre.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genre> rt = cq.from(Genre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
