
package com.etsmtl.equipe9.controllertest;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.etsmtl.equipe9.model.Adresse;
import com.etsmtl.equipe9.model.Forfait;
import com.etsmtl.equipe9.model.Cartecredit;
import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.test.exceptions.IllegalOrphanException;
import com.etsmtl.equipe9.test.exceptions.NonexistentEntityException;
import com.etsmtl.equipe9.test.exceptions.PreexistingEntityException;
import com.etsmtl.equipe9.test.exceptions.RollbackFailureException;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author NicolasSevigny
 */
public class ClientJpaController implements Serializable {

    public ClientJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (client.getLocations() == null) {
            client.setLocations(new HashSet<Location>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Adresse adresse = client.getAdresse();
            if (adresse != null) {
                adresse = em.getReference(adresse.getClass(), adresse.getIdadresse());
                client.setAdresse(adresse);
            }
            Forfait forfait = client.getForfait();
            if (forfait != null) {
                forfait = em.getReference(forfait.getClass(), forfait.getType());
                client.setForfait(forfait);
            }
            Cartecredit cartecredit = client.getCartecredit();
            if (cartecredit != null) {
                cartecredit = em.getReference(cartecredit.getClass(), cartecredit.getNumero());
                client.setCartecredit(cartecredit);
            }
            Set<Location> attachedLocations = new HashSet<Location>();
            for (Location locationsLocationToAttach : client.getLocations()) {
                locationsLocationToAttach = em.getReference(locationsLocationToAttach.getClass(), locationsLocationToAttach.getId());
                attachedLocations.add(locationsLocationToAttach);
            }
            client.setLocations(attachedLocations);
            em.persist(client);
            if (adresse != null) {
                adresse.getClients().add(client);
                adresse = em.merge(adresse);
            }
            if (forfait != null) {
                forfait.getClients().add(client);
                forfait = em.merge(forfait);
            }
            if (cartecredit != null) {
                cartecredit.getClients().add(client);
                cartecredit = em.merge(cartecredit);
            }
            for (Location locationsLocation : client.getLocations()) {
                Client oldClientOfLocationsLocation = locationsLocation.getClient();
                locationsLocation.setClient(client);
                locationsLocation = em.merge(locationsLocation);
                if (oldClientOfLocationsLocation != null) {
                    oldClientOfLocationsLocation.getLocations().remove(locationsLocation);
                    oldClientOfLocationsLocation = em.merge(oldClientOfLocationsLocation);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClient(client.getCourriel()) != null) {
                throw new PreexistingEntityException("Client " + client + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client persistentClient = em.find(Client.class, client.getCourriel());
            Adresse adresseOld = persistentClient.getAdresse();
            Adresse adresseNew = client.getAdresse();
            Forfait forfaitOld = persistentClient.getForfait();
            Forfait forfaitNew = client.getForfait();
            Cartecredit cartecreditOld = persistentClient.getCartecredit();
            Cartecredit cartecreditNew = client.getCartecredit();
            Set<Location> locationsOld = persistentClient.getLocations();
            Set<Location> locationsNew = client.getLocations();
            List<String> illegalOrphanMessages = null;
            for (Location locationsOldLocation : locationsOld) {
                if (!locationsNew.contains(locationsOldLocation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Location " + locationsOldLocation + " since its client field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (adresseNew != null) {
                adresseNew = em.getReference(adresseNew.getClass(), adresseNew.getIdadresse());
                client.setAdresse(adresseNew);
            }
            if (forfaitNew != null) {
                forfaitNew = em.getReference(forfaitNew.getClass(), forfaitNew.getType());
                client.setForfait(forfaitNew);
            }
            if (cartecreditNew != null) {
                cartecreditNew = em.getReference(cartecreditNew.getClass(), cartecreditNew.getNumero());
                client.setCartecredit(cartecreditNew);
            }
            Set<Location> attachedLocationsNew = new HashSet<Location>();
            for (Location locationsNewLocationToAttach : locationsNew) {
                locationsNewLocationToAttach = em.getReference(locationsNewLocationToAttach.getClass(), locationsNewLocationToAttach.getId());
                attachedLocationsNew.add(locationsNewLocationToAttach);
            }
            locationsNew = attachedLocationsNew;
            client.setLocations(locationsNew);
            client = em.merge(client);
            if (adresseOld != null && !adresseOld.equals(adresseNew)) {
                adresseOld.getClients().remove(client);
                adresseOld = em.merge(adresseOld);
            }
            if (adresseNew != null && !adresseNew.equals(adresseOld)) {
                adresseNew.getClients().add(client);
                adresseNew = em.merge(adresseNew);
            }
            if (forfaitOld != null && !forfaitOld.equals(forfaitNew)) {
                forfaitOld.getClients().remove(client);
                forfaitOld = em.merge(forfaitOld);
            }
            if (forfaitNew != null && !forfaitNew.equals(forfaitOld)) {
                forfaitNew.getClients().add(client);
                forfaitNew = em.merge(forfaitNew);
            }
            if (cartecreditOld != null && !cartecreditOld.equals(cartecreditNew)) {
                cartecreditOld.getClients().remove(client);
                cartecreditOld = em.merge(cartecreditOld);
            }
            if (cartecreditNew != null && !cartecreditNew.equals(cartecreditOld)) {
                cartecreditNew.getClients().add(client);
                cartecreditNew = em.merge(cartecreditNew);
            }
            for (Location locationsNewLocation : locationsNew) {
                if (!locationsOld.contains(locationsNewLocation)) {
                    Client oldClientOfLocationsNewLocation = locationsNewLocation.getClient();
                    locationsNewLocation.setClient(client);
                    locationsNewLocation = em.merge(locationsNewLocation);
                    if (oldClientOfLocationsNewLocation != null && !oldClientOfLocationsNewLocation.equals(client)) {
                        oldClientOfLocationsNewLocation.getLocations().remove(locationsNewLocation);
                        oldClientOfLocationsNewLocation = em.merge(oldClientOfLocationsNewLocation);
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
                String id = client.getCourriel();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getCourriel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Location> locationsOrphanCheck = client.getLocations();
            for (Location locationsOrphanCheckLocation : locationsOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Client (" + client + ") cannot be destroyed since the Location " + locationsOrphanCheckLocation + " in its locations field has a non-nullable client field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Adresse adresse = client.getAdresse();
            if (adresse != null) {
                adresse.getClients().remove(client);
                adresse = em.merge(adresse);
            }
            Forfait forfait = client.getForfait();
            if (forfait != null) {
                forfait.getClients().remove(client);
                forfait = em.merge(forfait);
            }
            Cartecredit cartecredit = client.getCartecredit();
            if (cartecredit != null) {
                cartecredit.getClients().remove(client);
                cartecredit = em.merge(cartecredit);
            }
            em.remove(client);
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

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
