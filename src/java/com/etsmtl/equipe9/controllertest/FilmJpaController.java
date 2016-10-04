
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Personne;
import com.etsmtl.equipe9.model.Pays;
import java.util.HashSet;
import java.util.Set;
import com.etsmtl.equipe9.model.Lienmedia;
import com.etsmtl.equipe9.model.Personnage;
import com.etsmtl.equipe9.model.Genre;
import com.etsmtl.equipe9.model.Exemplaire;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Scenariste;
import com.etsmtl.equipe9.test.exceptions.IllegalOrphanException;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author NicolasSevigny
 */
public class FilmJpaController implements Serializable {

    public FilmJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Film film) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (film.getPayses() == null) {
            film.setPayses(new HashSet<Pays>());
        }
        if (film.getLienmedias() == null) {
            film.setLienmedias(new HashSet<Lienmedia>());
        }
        if (film.getPersonnages() == null) {
            film.setPersonnages(new HashSet<Personnage>());
        }
        if (film.getGenres() == null) {
            film.setGenres(new HashSet<Genre>());
        }
        if (film.getExemplaires() == null) {
            film.setExemplaires(new HashSet<Exemplaire>());
        }
        if (film.getScenaristes() == null) {
            film.setScenaristes(new HashSet<Scenariste>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personne personne = film.getPersonne();
            if (personne != null) {
                personne = em.getReference(personne.getClass(), personne.getIdpersonne());
                film.setPersonne(personne);
            }
            Set<Pays> attachedPayses = new HashSet<Pays>();
            for (Pays paysesPaysToAttach : film.getPayses()) {
                paysesPaysToAttach = em.getReference(paysesPaysToAttach.getClass(), paysesPaysToAttach.getIdpays());
                attachedPayses.add(paysesPaysToAttach);
            }
            film.setPayses(attachedPayses);
            Set<Lienmedia> attachedLienmedias = new HashSet<Lienmedia>();
            for (Lienmedia lienmediasLienmediaToAttach : film.getLienmedias()) {
                lienmediasLienmediaToAttach = em.getReference(lienmediasLienmediaToAttach.getClass(), lienmediasLienmediaToAttach.getIdlienmedia());
                attachedLienmedias.add(lienmediasLienmediaToAttach);
            }
            film.setLienmedias(attachedLienmedias);
            Set<Personnage> attachedPersonnages = new HashSet<Personnage>();
            for (Personnage personnagesPersonnageToAttach : film.getPersonnages()) {
                personnagesPersonnageToAttach = em.getReference(personnagesPersonnageToAttach.getClass(), personnagesPersonnageToAttach.getId());
                attachedPersonnages.add(personnagesPersonnageToAttach);
            }
            film.setPersonnages(attachedPersonnages);
            Set<Genre> attachedGenres = new HashSet<Genre>();
            for (Genre genresGenreToAttach : film.getGenres()) {
                genresGenreToAttach = em.getReference(genresGenreToAttach.getClass(), genresGenreToAttach.getIdgenre());
                attachedGenres.add(genresGenreToAttach);
            }
            film.setGenres(attachedGenres);
            Set<Exemplaire> attachedExemplaires = new HashSet<Exemplaire>();
            for (Exemplaire exemplairesExemplaireToAttach : film.getExemplaires()) {
                exemplairesExemplaireToAttach = em.getReference(exemplairesExemplaireToAttach.getClass(), exemplairesExemplaireToAttach.getIdexemplaire());
                attachedExemplaires.add(exemplairesExemplaireToAttach);
            }
            film.setExemplaires(attachedExemplaires);
            Set<Scenariste> attachedScenaristes = new HashSet<Scenariste>();
            for (Scenariste scenaristesScenaristeToAttach : film.getScenaristes()) {
                scenaristesScenaristeToAttach = em.getReference(scenaristesScenaristeToAttach.getClass(), scenaristesScenaristeToAttach.getIdscenariste());
                attachedScenaristes.add(scenaristesScenaristeToAttach);
            }
            film.setScenaristes(attachedScenaristes);
            em.persist(film);
            if (personne != null) {
                personne.getFilms().add(film);
                personne = em.merge(personne);
            }
            for (Pays paysesPays : film.getPayses()) {
                paysesPays.getFilms().add(film);
                paysesPays = em.merge(paysesPays);
            }
            for (Lienmedia lienmediasLienmedia : film.getLienmedias()) {
                Film oldFilmOfLienmediasLienmedia = lienmediasLienmedia.getFilm();
                lienmediasLienmedia.setFilm(film);
                lienmediasLienmedia = em.merge(lienmediasLienmedia);
                if (oldFilmOfLienmediasLienmedia != null) {
                    oldFilmOfLienmediasLienmedia.getLienmedias().remove(lienmediasLienmedia);
                    oldFilmOfLienmediasLienmedia = em.merge(oldFilmOfLienmediasLienmedia);
                }
            }
            for (Personnage personnagesPersonnage : film.getPersonnages()) {
                Film oldFilmOfPersonnagesPersonnage = personnagesPersonnage.getFilm();
                personnagesPersonnage.setFilm(film);
                personnagesPersonnage = em.merge(personnagesPersonnage);
                if (oldFilmOfPersonnagesPersonnage != null) {
                    oldFilmOfPersonnagesPersonnage.getPersonnages().remove(personnagesPersonnage);
                    oldFilmOfPersonnagesPersonnage = em.merge(oldFilmOfPersonnagesPersonnage);
                }
            }
            for (Genre genresGenre : film.getGenres()) {
                genresGenre.getFilms().add(film);
                genresGenre = em.merge(genresGenre);
            }
            for (Exemplaire exemplairesExemplaire : film.getExemplaires()) {
                Film oldFilmOfExemplairesExemplaire = exemplairesExemplaire.getFilm();
                exemplairesExemplaire.setFilm(film);
                exemplairesExemplaire = em.merge(exemplairesExemplaire);
                if (oldFilmOfExemplairesExemplaire != null) {
                    oldFilmOfExemplairesExemplaire.getExemplaires().remove(exemplairesExemplaire);
                    oldFilmOfExemplairesExemplaire = em.merge(oldFilmOfExemplairesExemplaire);
                }
            }
            for (Scenariste scenaristesScenariste : film.getScenaristes()) {
                Film oldFilmOfScenaristesScenariste = scenaristesScenariste.getFilm();
                scenaristesScenariste.setFilm(film);
                scenaristesScenariste = em.merge(scenaristesScenariste);
                if (oldFilmOfScenaristesScenariste != null) {
                    oldFilmOfScenaristesScenariste.getScenaristes().remove(scenaristesScenariste);
                    oldFilmOfScenaristesScenariste = em.merge(oldFilmOfScenaristesScenariste);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findFilm(film.getIdfilm()) != null) {
                throw new PreexistingEntityException("Film " + film + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Film film) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Film persistentFilm = em.find(Film.class, film.getIdfilm());
            Personne personneOld = persistentFilm.getPersonne();
            Personne personneNew = film.getPersonne();
            Set<Pays> paysesOld = persistentFilm.getPayses();
            Set<Pays> paysesNew = film.getPayses();
            Set<Lienmedia> lienmediasOld = persistentFilm.getLienmedias();
            Set<Lienmedia> lienmediasNew = film.getLienmedias();
            Set<Personnage> personnagesOld = persistentFilm.getPersonnages();
            Set<Personnage> personnagesNew = film.getPersonnages();
            Set<Genre> genresOld = persistentFilm.getGenres();
            Set<Genre> genresNew = film.getGenres();
            Set<Exemplaire> exemplairesOld = persistentFilm.getExemplaires();
            Set<Exemplaire> exemplairesNew = film.getExemplaires();
            Set<Scenariste> scenaristesOld = persistentFilm.getScenaristes();
            Set<Scenariste> scenaristesNew = film.getScenaristes();
            List<String> illegalOrphanMessages = null;
            for (Personnage personnagesOldPersonnage : personnagesOld) {
                if (!personnagesNew.contains(personnagesOldPersonnage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personnage " + personnagesOldPersonnage + " since its film field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personneNew != null) {
                personneNew = em.getReference(personneNew.getClass(), personneNew.getIdpersonne());
                film.setPersonne(personneNew);
            }
            Set<Pays> attachedPaysesNew = new HashSet<Pays>();
            for (Pays paysesNewPaysToAttach : paysesNew) {
                paysesNewPaysToAttach = em.getReference(paysesNewPaysToAttach.getClass(), paysesNewPaysToAttach.getIdpays());
                attachedPaysesNew.add(paysesNewPaysToAttach);
            }
            paysesNew = attachedPaysesNew;
            film.setPayses(paysesNew);
            Set<Lienmedia> attachedLienmediasNew = new HashSet<Lienmedia>();
            for (Lienmedia lienmediasNewLienmediaToAttach : lienmediasNew) {
                lienmediasNewLienmediaToAttach = em.getReference(lienmediasNewLienmediaToAttach.getClass(), lienmediasNewLienmediaToAttach.getIdlienmedia());
                attachedLienmediasNew.add(lienmediasNewLienmediaToAttach);
            }
            lienmediasNew = attachedLienmediasNew;
            film.setLienmedias(lienmediasNew);
            Set<Personnage> attachedPersonnagesNew = new HashSet<Personnage>();
            for (Personnage personnagesNewPersonnageToAttach : personnagesNew) {
                personnagesNewPersonnageToAttach = em.getReference(personnagesNewPersonnageToAttach.getClass(), personnagesNewPersonnageToAttach.getId());
                attachedPersonnagesNew.add(personnagesNewPersonnageToAttach);
            }
            personnagesNew = attachedPersonnagesNew;
            film.setPersonnages(personnagesNew);
            Set<Genre> attachedGenresNew = new HashSet<Genre>();
            for (Genre genresNewGenreToAttach : genresNew) {
                genresNewGenreToAttach = em.getReference(genresNewGenreToAttach.getClass(), genresNewGenreToAttach.getIdgenre());
                attachedGenresNew.add(genresNewGenreToAttach);
            }
            genresNew = attachedGenresNew;
            film.setGenres(genresNew);
            Set<Exemplaire> attachedExemplairesNew = new HashSet<Exemplaire>();
            for (Exemplaire exemplairesNewExemplaireToAttach : exemplairesNew) {
                exemplairesNewExemplaireToAttach = em.getReference(exemplairesNewExemplaireToAttach.getClass(), exemplairesNewExemplaireToAttach.getIdexemplaire());
                attachedExemplairesNew.add(exemplairesNewExemplaireToAttach);
            }
            exemplairesNew = attachedExemplairesNew;
            film.setExemplaires(exemplairesNew);
            Set<Scenariste> attachedScenaristesNew = new HashSet<Scenariste>();
            for (Scenariste scenaristesNewScenaristeToAttach : scenaristesNew) {
                scenaristesNewScenaristeToAttach = em.getReference(scenaristesNewScenaristeToAttach.getClass(), scenaristesNewScenaristeToAttach.getIdscenariste());
                attachedScenaristesNew.add(scenaristesNewScenaristeToAttach);
            }
            scenaristesNew = attachedScenaristesNew;
            film.setScenaristes(scenaristesNew);
            film = em.merge(film);
            if (personneOld != null && !personneOld.equals(personneNew)) {
                personneOld.getFilms().remove(film);
                personneOld = em.merge(personneOld);
            }
            if (personneNew != null && !personneNew.equals(personneOld)) {
                personneNew.getFilms().add(film);
                personneNew = em.merge(personneNew);
            }
            for (Pays paysesOldPays : paysesOld) {
                if (!paysesNew.contains(paysesOldPays)) {
                    paysesOldPays.getFilms().remove(film);
                    paysesOldPays = em.merge(paysesOldPays);
                }
            }
            for (Pays paysesNewPays : paysesNew) {
                if (!paysesOld.contains(paysesNewPays)) {
                    paysesNewPays.getFilms().add(film);
                    paysesNewPays = em.merge(paysesNewPays);
                }
            }
            for (Lienmedia lienmediasOldLienmedia : lienmediasOld) {
                if (!lienmediasNew.contains(lienmediasOldLienmedia)) {
                    lienmediasOldLienmedia.setFilm(null);
                    lienmediasOldLienmedia = em.merge(lienmediasOldLienmedia);
                }
            }
            for (Lienmedia lienmediasNewLienmedia : lienmediasNew) {
                if (!lienmediasOld.contains(lienmediasNewLienmedia)) {
                    Film oldFilmOfLienmediasNewLienmedia = lienmediasNewLienmedia.getFilm();
                    lienmediasNewLienmedia.setFilm(film);
                    lienmediasNewLienmedia = em.merge(lienmediasNewLienmedia);
                    if (oldFilmOfLienmediasNewLienmedia != null && !oldFilmOfLienmediasNewLienmedia.equals(film)) {
                        oldFilmOfLienmediasNewLienmedia.getLienmedias().remove(lienmediasNewLienmedia);
                        oldFilmOfLienmediasNewLienmedia = em.merge(oldFilmOfLienmediasNewLienmedia);
                    }
                }
            }
            for (Personnage personnagesNewPersonnage : personnagesNew) {
                if (!personnagesOld.contains(personnagesNewPersonnage)) {
                    Film oldFilmOfPersonnagesNewPersonnage = personnagesNewPersonnage.getFilm();
                    personnagesNewPersonnage.setFilm(film);
                    personnagesNewPersonnage = em.merge(personnagesNewPersonnage);
                    if (oldFilmOfPersonnagesNewPersonnage != null && !oldFilmOfPersonnagesNewPersonnage.equals(film)) {
                        oldFilmOfPersonnagesNewPersonnage.getPersonnages().remove(personnagesNewPersonnage);
                        oldFilmOfPersonnagesNewPersonnage = em.merge(oldFilmOfPersonnagesNewPersonnage);
                    }
                }
            }
            for (Genre genresOldGenre : genresOld) {
                if (!genresNew.contains(genresOldGenre)) {
                    genresOldGenre.getFilms().remove(film);
                    genresOldGenre = em.merge(genresOldGenre);
                }
            }
            for (Genre genresNewGenre : genresNew) {
                if (!genresOld.contains(genresNewGenre)) {
                    genresNewGenre.getFilms().add(film);
                    genresNewGenre = em.merge(genresNewGenre);
                }
            }
            for (Exemplaire exemplairesOldExemplaire : exemplairesOld) {
                if (!exemplairesNew.contains(exemplairesOldExemplaire)) {
                    exemplairesOldExemplaire.setFilm(null);
                    exemplairesOldExemplaire = em.merge(exemplairesOldExemplaire);
                }
            }
            for (Exemplaire exemplairesNewExemplaire : exemplairesNew) {
                if (!exemplairesOld.contains(exemplairesNewExemplaire)) {
                    Film oldFilmOfExemplairesNewExemplaire = exemplairesNewExemplaire.getFilm();
                    exemplairesNewExemplaire.setFilm(film);
                    exemplairesNewExemplaire = em.merge(exemplairesNewExemplaire);
                    if (oldFilmOfExemplairesNewExemplaire != null && !oldFilmOfExemplairesNewExemplaire.equals(film)) {
                        oldFilmOfExemplairesNewExemplaire.getExemplaires().remove(exemplairesNewExemplaire);
                        oldFilmOfExemplairesNewExemplaire = em.merge(oldFilmOfExemplairesNewExemplaire);
                    }
                }
            }
            for (Scenariste scenaristesOldScenariste : scenaristesOld) {
                if (!scenaristesNew.contains(scenaristesOldScenariste)) {
                    scenaristesOldScenariste.setFilm(null);
                    scenaristesOldScenariste = em.merge(scenaristesOldScenariste);
                }
            }
            for (Scenariste scenaristesNewScenariste : scenaristesNew) {
                if (!scenaristesOld.contains(scenaristesNewScenariste)) {
                    Film oldFilmOfScenaristesNewScenariste = scenaristesNewScenariste.getFilm();
                    scenaristesNewScenariste.setFilm(film);
                    scenaristesNewScenariste = em.merge(scenaristesNewScenariste);
                    if (oldFilmOfScenaristesNewScenariste != null && !oldFilmOfScenaristesNewScenariste.equals(film)) {
                        oldFilmOfScenaristesNewScenariste.getScenaristes().remove(scenaristesNewScenariste);
                        oldFilmOfScenaristesNewScenariste = em.merge(oldFilmOfScenaristesNewScenariste);
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
                BigDecimal id = film.getIdfilm();
                if (findFilm(id) == null) {
                    throw new NonexistentEntityException("The film with id " + id + " no longer exists.");
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
            Film film;
            try {
                film = em.getReference(Film.class, id);
                film.getIdfilm();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The film with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Personnage> personnagesOrphanCheck = film.getPersonnages();
            for (Personnage personnagesOrphanCheckPersonnage : personnagesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Film (" + film + ") cannot be destroyed since the Personnage " + personnagesOrphanCheckPersonnage + " in its personnages field has a non-nullable film field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Personne personne = film.getPersonne();
            if (personne != null) {
                personne.getFilms().remove(film);
                personne = em.merge(personne);
            }
            Set<Pays> payses = film.getPayses();
            for (Pays paysesPays : payses) {
                paysesPays.getFilms().remove(film);
                paysesPays = em.merge(paysesPays);
            }
            Set<Lienmedia> lienmedias = film.getLienmedias();
            for (Lienmedia lienmediasLienmedia : lienmedias) {
                lienmediasLienmedia.setFilm(null);
                lienmediasLienmedia = em.merge(lienmediasLienmedia);
            }
            Set<Genre> genres = film.getGenres();
            for (Genre genresGenre : genres) {
                genresGenre.getFilms().remove(film);
                genresGenre = em.merge(genresGenre);
            }
            Set<Exemplaire> exemplaires = film.getExemplaires();
            for (Exemplaire exemplairesExemplaire : exemplaires) {
                exemplairesExemplaire.setFilm(null);
                exemplairesExemplaire = em.merge(exemplairesExemplaire);
            }
            Set<Scenariste> scenaristes = film.getScenaristes();
            for (Scenariste scenaristesScenariste : scenaristes) {
                scenaristesScenariste.setFilm(null);
                scenaristesScenariste = em.merge(scenaristesScenariste);
            }
            em.remove(film);
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

    public List<Film> findFilmEntities() {
        return findFilmEntities(true, -1, -1);
    }

    public List<Film> findFilmEntities(int maxResults, int firstResult) {
        return findFilmEntities(false, maxResults, firstResult);
    }

    private List<Film> findFilmEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Film.class));
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

    public Film findFilm(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Film.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilmCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Film> rt = cq.from(Film.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
