
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Personnage;
import com.etsmtl.equipe9.model.PersonnageId;
import com.etsmtl.equipe9.model.Personne;
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
public class PersonnageJpaController implements Serializable {

    public PersonnageJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personnage personnage) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (personnage.getId() == null) {
            personnage.setId(new PersonnageId());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Film film = personnage.getFilm();
            if (film != null) {
                film = em.getReference(film.getClass(), film.getIdfilm());
                personnage.setFilm(film);
            }
            Personne personne = personnage.getPersonne();
            if (personne != null) {
                personne = em.getReference(personne.getClass(), personne.getIdpersonne());
                personnage.setPersonne(personne);
            }
            em.persist(personnage);
            if (film != null) {
                film.getPersonnages().add(personnage);
                film = em.merge(film);
            }
            if (personne != null) {
                personne.getPersonnages().add(personnage);
                personne = em.merge(personne);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonnage(personnage.getId()) != null) {
                throw new PreexistingEntityException("Personnage " + personnage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personnage personnage) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personnage persistentPersonnage = em.find(Personnage.class, personnage.getId());
            Film filmOld = persistentPersonnage.getFilm();
            Film filmNew = personnage.getFilm();
            Personne personneOld = persistentPersonnage.getPersonne();
            Personne personneNew = personnage.getPersonne();
            if (filmNew != null) {
                filmNew = em.getReference(filmNew.getClass(), filmNew.getIdfilm());
                personnage.setFilm(filmNew);
            }
            if (personneNew != null) {
                personneNew = em.getReference(personneNew.getClass(), personneNew.getIdpersonne());
                personnage.setPersonne(personneNew);
            }
            personnage = em.merge(personnage);
            if (filmOld != null && !filmOld.equals(filmNew)) {
                filmOld.getPersonnages().remove(personnage);
                filmOld = em.merge(filmOld);
            }
            if (filmNew != null && !filmNew.equals(filmOld)) {
                filmNew.getPersonnages().add(personnage);
                filmNew = em.merge(filmNew);
            }
            if (personneOld != null && !personneOld.equals(personneNew)) {
                personneOld.getPersonnages().remove(personnage);
                personneOld = em.merge(personneOld);
            }
            if (personneNew != null && !personneNew.equals(personneOld)) {
                personneNew.getPersonnages().add(personnage);
                personneNew = em.merge(personneNew);
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
                PersonnageId id = personnage.getId();
                if (findPersonnage(id) == null) {
                    throw new NonexistentEntityException("The personnage with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PersonnageId id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personnage personnage;
            try {
                personnage = em.getReference(Personnage.class, id);
                personnage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personnage with id " + id + " no longer exists.", enfe);
            }
            Film film = personnage.getFilm();
            if (film != null) {
                film.getPersonnages().remove(personnage);
                film = em.merge(film);
            }
            Personne personne = personnage.getPersonne();
            if (personne != null) {
                personne.getPersonnages().remove(personnage);
                personne = em.merge(personne);
            }
            em.remove(personnage);
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

    public List<Personnage> findPersonnageEntities() {
        return findPersonnageEntities(true, -1, -1);
    }

    public List<Personnage> findPersonnageEntities(int maxResults, int firstResult) {
        return findPersonnageEntities(false, maxResults, firstResult);
    }

    private List<Personnage> findPersonnageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personnage.class));
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

    public Personnage findPersonnage(PersonnageId id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personnage.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonnageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personnage> rt = cq.from(Personnage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
