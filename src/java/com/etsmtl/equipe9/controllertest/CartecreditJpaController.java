
package com.etsmtl.equipe9.controllertest;

import com.etsmtl.equipe9.model.Cartecredit;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Client;
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
public class CartecreditJpaController implements Serializable {

    public CartecreditJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cartecredit cartecredit) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cartecredit.getClients() == null) {
            cartecredit.setClients(new HashSet<Client>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Set<Client> attachedClients = new HashSet<Client>();
            for (Client clientsClientToAttach : cartecredit.getClients()) {
                clientsClientToAttach = em.getReference(clientsClientToAttach.getClass(), clientsClientToAttach.getCourriel());
                attachedClients.add(clientsClientToAttach);
            }
            cartecredit.setClients(attachedClients);
            em.persist(cartecredit);
            for (Client clientsClient : cartecredit.getClients()) {
                Cartecredit oldCartecreditOfClientsClient = clientsClient.getCartecredit();
                clientsClient.setCartecredit(cartecredit);
                clientsClient = em.merge(clientsClient);
                if (oldCartecreditOfClientsClient != null) {
                    oldCartecreditOfClientsClient.getClients().remove(clientsClient);
                    oldCartecreditOfClientsClient = em.merge(oldCartecreditOfClientsClient);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCartecredit(cartecredit.getNumero()) != null) {
                throw new PreexistingEntityException("Cartecredit " + cartecredit + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cartecredit cartecredit) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cartecredit persistentCartecredit = em.find(Cartecredit.class, cartecredit.getNumero());
            Set<Client> clientsOld = persistentCartecredit.getClients();
            Set<Client> clientsNew = cartecredit.getClients();
            Set<Client> attachedClientsNew = new HashSet<Client>();
            for (Client clientsNewClientToAttach : clientsNew) {
                clientsNewClientToAttach = em.getReference(clientsNewClientToAttach.getClass(), clientsNewClientToAttach.getCourriel());
                attachedClientsNew.add(clientsNewClientToAttach);
            }
            clientsNew = attachedClientsNew;
            cartecredit.setClients(clientsNew);
            cartecredit = em.merge(cartecredit);
            for (Client clientsOldClient : clientsOld) {
                if (!clientsNew.contains(clientsOldClient)) {
                    clientsOldClient.setCartecredit(null);
                    clientsOldClient = em.merge(clientsOldClient);
                }
            }
            for (Client clientsNewClient : clientsNew) {
                if (!clientsOld.contains(clientsNewClient)) {
                    Cartecredit oldCartecreditOfClientsNewClient = clientsNewClient.getCartecredit();
                    clientsNewClient.setCartecredit(cartecredit);
                    clientsNewClient = em.merge(clientsNewClient);
                    if (oldCartecreditOfClientsNewClient != null && !oldCartecreditOfClientsNewClient.equals(cartecredit)) {
                        oldCartecreditOfClientsNewClient.getClients().remove(clientsNewClient);
                        oldCartecreditOfClientsNewClient = em.merge(oldCartecreditOfClientsNewClient);
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
                String id = cartecredit.getNumero();
                if (findCartecredit(id) == null) {
                    throw new NonexistentEntityException("The cartecredit with id " + id + " no longer exists.");
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
            Cartecredit cartecredit;
            try {
                cartecredit = em.getReference(Cartecredit.class, id);
                cartecredit.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cartecredit with id " + id + " no longer exists.", enfe);
            }
            Set<Client> clients = cartecredit.getClients();
            for (Client clientsClient : clients) {
                clientsClient.setCartecredit(null);
                clientsClient = em.merge(clientsClient);
            }
            em.remove(cartecredit);
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

    public List<Cartecredit> findCartecreditEntities() {
        return findCartecreditEntities(true, -1, -1);
    }

    public List<Cartecredit> findCartecreditEntities(int maxResults, int firstResult) {
        return findCartecreditEntities(false, maxResults, firstResult);
    }

    private List<Cartecredit> findCartecreditEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cartecredit.class));
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

    public Cartecredit findCartecredit(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cartecredit.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartecreditCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cartecredit> rt = cq.from(Cartecredit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
