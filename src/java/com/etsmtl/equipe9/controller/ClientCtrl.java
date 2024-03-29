package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.service.DAOFactory;
import com.etsmtl.equipe9.model.Client;
import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xml.security.utils.Base64;


public class ClientCtrl {
    
    ClientDAO dao = DAOFactory.getInstance().getClientDAO();
    HashPassword hash = new HashPassword();
    
    public ClientCtrl() {
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
    
    public static void main(String[] args) {
        ClientCtrl controleur = new ClientCtrl();
        HashPassword hash = new HashPassword();
        
        try {
            List<Client> liste = controleur.getAllClient();
//            System.out.println(liste.size());
//            
//            Client test = controleur.getClient("qwe@qwe.qwe");
//            
//            String gg = hash.get_SHA_256_SecurePassword("123456789");
//            test.setMotpasse(gg);
//            
//            controleur.updateClientMotPasse(test, gg);
            
            
            for(Client c: liste){
                String hashedPassword = hash.get_SHA_256_SecurePassword(c.getMotpasse());
                controleur.updateClientMotPasse(c, hashedPassword);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
