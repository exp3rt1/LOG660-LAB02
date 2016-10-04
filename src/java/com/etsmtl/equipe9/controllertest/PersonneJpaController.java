
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Personnage;
import java.util.HashSet;
import java.util.Set;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Personne;
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
public class PersonneJpaController implements Serializable {

    public PersonneJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personne personne) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (personne.getPersonnages() == null) {
            personne.setPersonnages(new HashSet<Personnage>());
        }
        if (personne.getFilms() == null) {
            personne.setFilms(new HashSet<Film>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Set<Personnage> attachedPersonnages = new HashSet<Personnage>();
            for (Personnage personnagesPersonnageToAttach : personne.getPersonnages()) {
                personnagesPersonnageToAttach = em.getReference(personnagesPersonnageToAttach.getClass(), personnagesPersonnageToAttach.getId());
                attachedPersonnages.add(personnagesPersonnageToAttach);
            }
            personne.setPersonnages(attachedPersonnages);
            Set<Film> attachedFilms = new HashSet<Film>();
            for (Film filmsFilmToAttach : personne.getFilms()) {
                filmsFilmToAttach = em.getReference(filmsFilmToAttach.getClass(), filmsFilmToAttach.getIdfilm());
                attachedFilms.add(filmsFilmToAttach);
            }
            personne.setFilms(attachedFilms);
            em.persist(personne);
            for (Personnage personnagesPersonnage : personne.getPersonnages()) {
                Personne oldPersonneOfPersonnagesPersonnage = personnagesPersonnage.getPersonne();
                personnagesPersonnage.setPersonne(personne);
                personnagesPersonnage = em.merge(personnagesPersonnage);
                if (oldPersonneOfPersonnagesPersonnage != null) {
                    oldPersonneOfPersonnagesPersonnage.getPersonnages().remove(personnagesPersonnage);
                    oldPersonneOfPersonnagesPersonnage = em.merge(oldPersonneOfPersonnagesPersonnage);
                }
            }
            for (Film filmsFilm : personne.getFilms()) {
                Personne oldPersonneOfFilmsFilm = filmsFilm.getPersonne();
                filmsFilm.setPersonne(personne);
                filmsFilm = em.merge(filmsFilm);
                if (oldPersonneOfFilmsFilm != null) {
                    oldPersonneOfFilmsFilm.getFilms().remove(filmsFilm);
                    oldPersonneOfFilmsFilm = em.merge(oldPersonneOfFilmsFilm);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonne(personne.getIdpersonne()) != null) {
                throw new PreexistingEntityException("Personne " + personne + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personne personne) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personne persistentPersonne = em.find(Personne.class, personne.getIdpersonne());
            Set<Personnage> personnagesOld = persistentPersonne.getPersonnages();
            Set<Personnage> personnagesNew = personne.getPersonnages();
            Set<Film> filmsOld = persistentPersonne.getFilms();
            Set<Film> filmsNew = personne.getFilms();
            List<String> illegalOrphanMessages = null;
            for (Personnage personnagesOldPersonnage : personnagesOld) {
                if (!personnagesNew.contains(personnagesOldPersonnage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personnage " + personnagesOldPersonnage + " since its personne field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Personnage> attachedPersonnagesNew = new HashSet<Personnage>();
            for (Personnage personnagesNewPersonnageToAttach : personnagesNew) {
                personnagesNewPersonnageToAttach = em.getReference(personnagesNewPersonnageToAttach.getClass(), personnagesNewPersonnageToAttach.getId());
                attachedPersonnagesNew.add(personnagesNewPersonnageToAttach);
            }
            personnagesNew = attachedPersonnagesNew;
            personne.setPersonnages(personnagesNew);
            Set<Film> attachedFilmsNew = new HashSet<Film>();
            for (Film filmsNewFilmToAttach : filmsNew) {
                filmsNewFilmToAttach = em.getReference(filmsNewFilmToAttach.getClass(), filmsNewFilmToAttach.getIdfilm());
                attachedFilmsNew.add(filmsNewFilmToAttach);
            }
            filmsNew = attachedFilmsNew;
            personne.setFilms(filmsNew);
            personne = em.merge(personne);
            for (Personnage personnagesNewPersonnage : personnagesNew) {
                if (!personnagesOld.contains(personnagesNewPersonnage)) {
                    Personne oldPersonneOfPersonnagesNewPersonnage = personnagesNewPersonnage.getPersonne();
                    personnagesNewPersonnage.setPersonne(personne);
                    personnagesNewPersonnage = em.merge(personnagesNewPersonnage);
                    if (oldPersonneOfPersonnagesNewPersonnage != null && !oldPersonneOfPersonnagesNewPersonnage.equals(personne)) {
                        oldPersonneOfPersonnagesNewPersonnage.getPersonnages().remove(personnagesNewPersonnage);
                        oldPersonneOfPersonnagesNewPersonnage = em.merge(oldPersonneOfPersonnagesNewPersonnage);
                    }
                }
            }
            for (Film filmsOldFilm : filmsOld) {
                if (!filmsNew.contains(filmsOldFilm)) {
                    filmsOldFilm.setPersonne(null);
                    filmsOldFilm = em.merge(filmsOldFilm);
                }
            }
            for (Film filmsNewFilm : filmsNew) {
                if (!filmsOld.contains(filmsNewFilm)) {
                    Personne oldPersonneOfFilmsNewFilm = filmsNewFilm.getPersonne();
                    filmsNewFilm.setPersonne(personne);
                    filmsNewFilm = em.merge(filmsNewFilm);
                    if (oldPersonneOfFilmsNewFilm != null && !oldPersonneOfFilmsNewFilm.equals(personne)) {
                        oldPersonneOfFilmsNewFilm.getFilms().remove(filmsNewFilm);
                        oldPersonneOfFilmsNewFilm = em.merge(oldPersonneOfFilmsNewFilm);
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
                BigDecimal id = personne.getIdpersonne();
                if (findPersonne(id) == null) {
                    throw new NonexistentEntityException("The personne with id " + id + " no longer exists.");
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
            Personne personne;
            try {
                personne = em.getReference(Personne.class, id);
                personne.getIdpersonne();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personne with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Personnage> personnagesOrphanCheck = personne.getPersonnages();
            for (Personnage personnagesOrphanCheckPersonnage : personnagesOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personne (" + personne + ") cannot be destroyed since the Personnage " + personnagesOrphanCheckPersonnage + " in its personnages field has a non-nullable personne field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Film> films = personne.getFilms();
            for (Film filmsFilm : films) {
                filmsFilm.setPersonne(null);
                filmsFilm = em.merge(filmsFilm);
            }
            em.remove(personne);
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

    public List<Personne> findPersonneEntities() {
        return findPersonneEntities(true, -1, -1);
    }

    public List<Personne> findPersonneEntities(int maxResults, int firstResult) {
        return findPersonneEntities(false, maxResults, firstResult);
    }

    private List<Personne> findPersonneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personne.class));
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

    public Personne findPersonne(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personne.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personne> rt = cq.from(Personne.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
