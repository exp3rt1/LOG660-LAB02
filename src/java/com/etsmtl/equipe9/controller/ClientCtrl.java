package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.service.DAOFactory;



public class ClientCtrl {
    
    ClientDAO dao = DAOFactory.getInstance().getClientDAO();
    
    public boolean getPassword (String courriel, String motDePasse){
        // Quand on va avoir hasher le mot de passe, il faudra le dehasher
        String motDePasseBD = dao.findById(courriel).getMotpasse();
        
        return motDePasse.equals(motDePasseBD);
    }
    
    public static void main(String[] args) {
        ClientCtrl pass = new ClientCtrl();
        
        System.out.println(pass.getPassword("RobertBSutton56@yahoo.com","allo"));
    }
}
