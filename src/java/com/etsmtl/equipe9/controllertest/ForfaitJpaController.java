
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.model.Forfait;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
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
public class ForfaitJpaController implements Serializable {

    public ForfaitJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Forfait forfait) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (forfait.getClients() == null) {
            forfait.setClients(new HashSet<Client>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Set<Client> attachedClients = new HashSet<Client>();
            for (Client clientsClientToAttach : forfait.getClients()) {
                clientsClientToAttach = em.getReference(clientsClientToAttach.getClass(), clientsClientToAttach.getCourriel());
                attachedClients.add(clientsClientToAttach);
            }
            forfait.setClients(attachedClients);
            em.persist(forfait);
            for (Client clientsClient : forfait.getClients()) {
                Forfait oldForfaitOfClientsClient = clientsClient.getForfait();
                clientsClient.setForfait(forfait);
                clientsClient = em.merge(clientsClient);
                if (oldForfaitOfClientsClient != null) {
                    oldForfaitOfClientsClient.getClients().remove(clientsClient);
                    oldForfaitOfClientsClient = em.merge(oldForfaitOfClientsClient);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findForfait(forfait.getType()) != null) {
                throw new PreexistingEntityException("Forfait " + forfait + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Forfait forfait) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Forfait persistentForfait = em.find(Forfait.class, forfait.getType());
            Set<Client> clientsOld = persistentForfait.getClients();
            Set<Client> clientsNew = forfait.getClients();
            Set<Client> attachedClientsNew = new HashSet<Client>();
            for (Client clientsNewClientToAttach : clientsNew) {
                clientsNewClientToAttach = em.getReference(clientsNewClientToAttach.getClass(), clientsNewClientToAttach.getCourriel());
                attachedClientsNew.add(clientsNewClientToAttach);
            }
            clientsNew = attachedClientsNew;
            forfait.setClients(clientsNew);
            forfait = em.merge(forfait);
            for (Client clientsOldClient : clientsOld) {
                if (!clientsNew.contains(clientsOldClient)) {
                    clientsOldClient.setForfait(null);
                    clientsOldClient = em.merge(clientsOldClient);
                }
            }
            for (Client clientsNewClient : clientsNew) {
                if (!clientsOld.contains(clientsNewClient)) {
                    Forfait oldForfaitOfClientsNewClient = clientsNewClient.getForfait();
                    clientsNewClient.setForfait(forfait);
                    clientsNewClient = em.merge(clientsNewClient);
                    if (oldForfaitOfClientsNewClient != null && !oldForfaitOfClientsNewClient.equals(forfait)) {
                        oldForfaitOfClientsNewClient.getClients().remove(clientsNewClient);
                        oldForfaitOfClientsNewClient = em.merge(oldForfaitOfClientsNewClient);
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
                char id = forfait.getType();
                if (findForfait(id) == null) {
                    throw new NonexistentEntityException("The forfait with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(char id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Forfait forfait;
            try {
                forfait = em.getReference(Forfait.class, id);
                forfait.getType();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The forfait with id " + id + " no longer exists.", enfe);
            }
            Set<Client> clients = forfait.getClients();
            for (Client clientsClient : clients) {
                clientsClient.setForfait(null);
                clientsClient = em.merge(clientsClient);
            }
            em.remove(forfait);
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

    public List<Forfait> findForfaitEntities() {
        return findForfaitEntities(true, -1, -1);
    }

    public List<Forfait> findForfaitEntities(int maxResults, int firstResult) {
        return findForfaitEntities(false, maxResults, firstResult);
    }

    private List<Forfait> findForfaitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Forfait.class));
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

    public Forfait findForfait(char id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Forfait.class, id);
        } finally {
            em.close();
        }
    }

    public int getForfaitCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Forfait> rt = cq.from(Forfait.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
