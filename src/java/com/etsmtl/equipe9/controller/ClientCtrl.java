package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import com.etsmtl.equipe9.model.Client;
import java.util.Date;
import java.util.List;


public class ClientCtrl {
    
    ClientDAO dao = DAOFactory.getInstance().getClientDAO();
    HashPassword hash = new HashPassword();
    
    public ClientCtrl() {
    }

    public boolean createClient (String courriel, String nom, String prenom, String motDePasse, String numeroTelephone, Date dateNaissance){
        Client newClient = new Client(courriel, nom, prenom, hash.get_SHA_256_SecurePassword(motDePasse), numeroTelephone, dateNaissance);
        return dao.insert(newClient);
    }
    
    public boolean deleteClient (String courriel){
        return dao.delete(getClient(courriel));
    }
    
    // vérifier si le mot de passe de l'utilisateur est bon
    public boolean checkPassword (String courriel, String motDePasse){
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
        return dao.updateMotPasse(client.getCourriel(), hash.get_SHA_256_SecurePassword(newPassword));
    }
}
