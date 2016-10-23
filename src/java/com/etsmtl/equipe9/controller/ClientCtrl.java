package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import com.etsmtl.equipe9.model.Client;
import java.util.List;
import org.apache.xml.security.utils.Base64;


public class ClientCtrl {
    
    ClientDAO dao = DAOFactory.getInstance().getClientDAO();
    HashPassword hash = new HashPassword();
    
    public ClientCtrl() {
    }
    
    public boolean getPassword (String courriel, String motDePasse){
        // Quand on va avoir hasher le mot de passe, il faudra le dehasher
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
    
    public static void main(String[] args) {
        byte[] salt = null;
        ClientCtrl controleur = new ClientCtrl();
        HashPassword hash = new HashPassword();
        
        try {
            List<Client> liste = controleur.getAllClient();
            System.out.println(liste.size());
            
            Client test = controleur.getClient("asd@asd.asd");
            test.setMotpasse(hash.get_SHA_256_SecurePassword(test.getMotpasse()));
            System.out.println(controleur.updateClient(test)); // supposer de retourner true
            
            /*for(Client c: liste){
                System.out.println(c.getCourriel());
                //c.setMotpasse(hash.get_SHA_256_SecurePassword(c.getMotpasse()));
                //controleur.updateClient(c);
            }*/
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
