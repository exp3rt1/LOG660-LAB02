package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.model.Client;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

public class ClientDAO extends DAOAbstrait<Client, String>{
    
    @Override
    public List<Client> findAll() {
        
        try {
            connect();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            Query query = em.createNamedQuery("Client.findAll");
            List<Object[]> liste = query.getResultList();
            List<Client> listeClient = new ArrayList<Client>();
            
            for(Object[] result : liste) 
            {
               String courriel = (String) result[0];
               String motDePasse = (String) result[1];
               listeClient.add(new Client(courriel, motDePasse));              
            }
            
            return listeClient;
            //return this.em.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnect();
        }
    }
    
    @Override
    public Client findById(String courriel) {
        try {
            connect();
            return this.em.find(Client.class, courriel);
        } catch (Exception e) {
            return null;
        } finally {
            disconnect();
        }
    }
    
    @Override
    public List<Client> findById(List<String> listeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insert(Client obj) {
        try {
            connect();
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            disconnect();
        }
    }

    @Override
    public boolean update(Client obj) {
        try {
            connect();
            em.getTransaction().begin();
            Client merge = em.merge(obj);
            em.getTransaction().commit();
            return merge != null;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            disconnect();
        }
    }
    
    public boolean updateMotPasse(String courrielClient, String newPassword){
        try {
            connect();
            em.getTransaction().begin();
            Client c = em.find(Client.class, courrielClient);
            c.setMotpasse(newPassword);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            disconnect();
        }
    }

    @Override
    public boolean delete(Client obj) {
        try {
            connect();
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            disconnect();
        }
    }
    
}
