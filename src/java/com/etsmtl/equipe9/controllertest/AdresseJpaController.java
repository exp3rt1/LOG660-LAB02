
package com.etsmtl.equipe9.controllertest;

import com.etsmtl.equipe9.model.Adresse;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Client;
import java.util.HashSet;
import java.util.Set;
import com.etsmtl.equipe9.model.Employe;
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
public class AdresseJpaController implements Serializable {

    public AdresseJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Adresse adresse) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (adresse.getClients() == null) {
            adresse.setClients(new HashSet<Client>());
        }
        if (adresse.getEmployes() == null) {
            adresse.setEmployes(new HashSet<Employe>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Set<Client> attachedClients = new HashSet<Client>();
            for (Client clientsClientToAttach : adresse.getClients()) {
                clientsClientToAttach = em.getReference(clientsClientToAttach.getClass(), clientsClientToAttach.getCourriel());
                attachedClients.add(clientsClientToAttach);
            }
            adresse.setClients(attachedClients);
            Set<Employe> attachedEmployes = new HashSet<Employe>();
            for (Employe employesEmployeToAttach : adresse.getEmployes()) {
                employesEmployeToAttach = em.getReference(employesEmployeToAttach.getClass(), employesEmployeToAttach.getMatriculeemploye());
                attachedEmployes.add(employesEmployeToAttach);
            }
            adresse.setEmployes(attachedEmployes);
            em.persist(adresse);
            for (Client clientsClient : adresse.getClients()) {
                Adresse oldAdresseOfClientsClient = clientsClient.getAdresse();
                clientsClient.setAdresse(adresse);
                clientsClient = em.merge(clientsClient);
                if (oldAdresseOfClientsClient != null) {
                    oldAdresseOfClientsClient.getClients().remove(clientsClient);
                    oldAdresseOfClientsClient = em.merge(oldAdresseOfClientsClient);
                }
            }
            for (Employe employesEmploye : adresse.getEmployes()) {
                Adresse oldAdresseOfEmployesEmploye = employesEmploye.getAdresse();
                employesEmploye.setAdresse(adresse);
                employesEmploye = em.merge(employesEmploye);
                if (oldAdresseOfEmployesEmploye != null) {
                    oldAdresseOfEmployesEmploye.getEmployes().remove(employesEmploye);
                    oldAdresseOfEmployesEmploye = em.merge(oldAdresseOfEmployesEmploye);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAdresse(adresse.getIdadresse()) != null) {
                throw new PreexistingEntityException("Adresse " + adresse + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Adresse adresse) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Adresse persistentAdresse = em.find(Adresse.class, adresse.getIdadresse());
            Set<Client> clientsOld = persistentAdresse.getClients();
            Set<Client> clientsNew = adresse.getClients();
            Set<Employe> employesOld = persistentAdresse.getEmployes();
            Set<Employe> employesNew = adresse.getEmployes();
            Set<Client> attachedClientsNew = new HashSet<Client>();
            for (Client clientsNewClientToAttach : clientsNew) {
                clientsNewClientToAttach = em.getReference(clientsNewClientToAttach.getClass(), clientsNewClientToAttach.getCourriel());
                attachedClientsNew.add(clientsNewClientToAttach);
            }
            clientsNew = attachedClientsNew;
            adresse.setClients(clientsNew);
            Set<Employe> attachedEmployesNew = new HashSet<Employe>();
            for (Employe employesNewEmployeToAttach : employesNew) {
                employesNewEmployeToAttach = em.getReference(employesNewEmployeToAttach.getClass(), employesNewEmployeToAttach.getMatriculeemploye());
                attachedEmployesNew.add(employesNewEmployeToAttach);
            }
            employesNew = attachedEmployesNew;
            adresse.setEmployes(employesNew);
            adresse = em.merge(adresse);
            for (Client clientsOldClient : clientsOld) {
                if (!clientsNew.contains(clientsOldClient)) {
                    clientsOldClient.setAdresse(null);
                    clientsOldClient = em.merge(clientsOldClient);
                }
            }
            for (Client clientsNewClient : clientsNew) {
                if (!clientsOld.contains(clientsNewClient)) {
                    Adresse oldAdresseOfClientsNewClient = clientsNewClient.getAdresse();
                    clientsNewClient.setAdresse(adresse);
                    clientsNewClient = em.merge(clientsNewClient);
                    if (oldAdresseOfClientsNewClient != null && !oldAdresseOfClientsNewClient.equals(adresse)) {
                        oldAdresseOfClientsNewClient.getClients().remove(clientsNewClient);
                        oldAdresseOfClientsNewClient = em.merge(oldAdresseOfClientsNewClient);
                    }
                }
            }
            for (Employe employesOldEmploye : employesOld) {
                if (!employesNew.contains(employesOldEmploye)) {
                    employesOldEmploye.setAdresse(null);
                    employesOldEmploye = em.merge(employesOldEmploye);
                }
            }
            for (Employe employesNewEmploye : employesNew) {
                if (!employesOld.contains(employesNewEmploye)) {
                    Adresse oldAdresseOfEmployesNewEmploye = employesNewEmploye.getAdresse();
                    employesNewEmploye.setAdresse(adresse);
                    employesNewEmploye = em.merge(employesNewEmploye);
                    if (oldAdresseOfEmployesNewEmploye != null && !oldAdresseOfEmployesNewEmploye.equals(adresse)) {
                        oldAdresseOfEmployesNewEmploye.getEmployes().remove(employesNewEmploye);
                        oldAdresseOfEmployesNewEmploye = em.merge(oldAdresseOfEmployesNewEmploye);
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
                BigDecimal id = adresse.getIdadresse();
                if (findAdresse(id) == null) {
                    throw new NonexistentEntityException("The adresse with id " + id + " no longer exists.");
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
            Adresse adresse;
            try {
                adresse = em.getReference(Adresse.class, id);
                adresse.getIdadresse();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adresse with id " + id + " no longer exists.", enfe);
            }
            Set<Client> clients = adresse.getClients();
            for (Client clientsClient : clients) {
                clientsClient.setAdresse(null);
                clientsClient = em.merge(clientsClient);
            }
            Set<Employe> employes = adresse.getEmployes();
            for (Employe employesEmploye : employes) {
                employesEmploye.setAdresse(null);
                employesEmploye = em.merge(employesEmploye);
            }
            em.remove(adresse);
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

    public List<Adresse> findAdresseEntities() {
        return findAdresseEntities(true, -1, -1);
    }

    public List<Adresse> findAdresseEntities(int maxResults, int firstResult) {
        return findAdresseEntities(false, maxResults, firstResult);
    }

    private List<Adresse> findAdresseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Adresse.class));
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

    public Adresse findAdresse(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Adresse.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdresseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Adresse> rt = cq.from(Adresse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
