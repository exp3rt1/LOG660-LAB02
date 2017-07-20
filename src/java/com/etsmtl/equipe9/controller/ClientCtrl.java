package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import com.etsmtl.equipe9.model.Client;
import java.util.List;


public class ClientCtrl {
    
    ClientDAO dao = DAOFactory.getInstance().getClientDAO();
    HashPassword hash = new HashPassword();
    
    public ClientCtrl() {
    }
    
    public boolean createClient (String courriel, String motDePasse){
        Client newClient = new Client(courriel, motDePasse);
        return dao.insert(newClient);
    }
    
    public boolean deleteClient (String courriel){
        return dao.delete(getClient(courriel));
    }
    
    public boolean getPassword (String courriel, String motDePasse){
        String motDePasseBD = this.getClient(courriel).getMotpasse();
        String hashedMotDePasse = hash.get_SHA_256_SecurePassword(motDePasse);
        return hashedMotDePasse.equals(motDePasseBD);
    }
    
    public Client getClient(String courriel) {
        return dao.findById(courriel);
    }
    
    public List<Client> getAllClient(){
        return dao.findAll();
    }
    
    public boolean updateClient(Client client){
        return dao.update(client);
    }
    
    public boolean updateClientMotPasse(Client client, String newPassword){
        return dao.updateMotPasse(client.getCourriel(), newPassword);
    }
}
