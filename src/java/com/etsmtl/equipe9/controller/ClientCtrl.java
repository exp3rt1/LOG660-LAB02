package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;



public class ClientCtrl {
    
    public boolean getPassword (String courriel, String motDePasse){
        // Quand on va avoir hasher le mot de passe, il faudra le dehasher
        ClientDAO dao = new ClientDAO();
        String motDePasseBD = dao.findById(courriel).getMotpasse();
        
        if (motDePasse.equals(motDePasseBD)){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        //ClientCtrl pass = new ClientCtrl();
        //String password = pass.getPassword("RobertBSutton56@yahoo.com");
        //System.out.println("Le password est: "+ password);
    }
}
