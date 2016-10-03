
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Adresse;
import com.etsmtl.equipe9.model.Employe;
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
public class EmployeJpaController implements Serializable {

    public EmployeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employe employe) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Adresse adresse = employe.getAdresse();
            if (adresse != null) {
                adresse = em.getReference(adresse.getClass(), adresse.getIdadresse());
                employe.setAdresse(adresse);
            }
            em.persist(employe);
            if (adresse != null) {
                adresse.getEmployes().add(employe);
                adresse = em.merge(adresse);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmploye(employe.getMatriculeemploye()) != null) {
                throw new PreexistingEntityException("Employe " + employe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employe employe) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employe persistentEmploye = em.find(Employe.class, employe.getMatriculeemploye());
            Adresse adresseOld = persistentEmploye.getAdresse();
            Adresse adresseNew = employe.getAdresse();
            if (adresseNew != null) {
                adresseNew = em.getReference(adresseNew.getClass(), adresseNew.getIdadresse());
                employe.setAdresse(adresseNew);
            }
            employe = em.merge(employe);
            if (adresseOld != null && !adresseOld.equals(adresseNew)) {
                adresseOld.getEmployes().remove(employe);
                adresseOld = em.merge(adresseOld);
            }
            if (adresseNew != null && !adresseNew.equals(adresseOld)) {
                adresseNew.getEmployes().add(employe);
                adresseNew = em.merge(adresseNew);
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
                String id = employe.getMatriculeemploye();
                if (findEmploye(id) == null) {
                    throw new NonexistentEntityException("The employe with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employe employe;
            try {
                employe = em.getReference(Employe.class, id);
                employe.getMatriculeemploye();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employe with id " + id + " no longer exists.", enfe);
            }
            Adresse adresse = employe.getAdresse();
            if (adresse != null) {
                adresse.getEmployes().remove(employe);
                adresse = em.merge(adresse);
            }
            em.remove(employe);
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

    public List<Employe> findEmployeEntities() {
        return findEmployeEntities(true, -1, -1);
    }

    public List<Employe> findEmployeEntities(int maxResults, int firstResult) {
        return findEmployeEntities(false, maxResults, firstResult);
    }

    private List<Employe> findEmployeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employe.class));
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

    public Employe findEmploye(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employe.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employe> rt = cq.from(Employe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
