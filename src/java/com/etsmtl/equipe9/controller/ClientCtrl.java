package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;



public class ClientCtrl {
    
    public String getPassword (String courriel){
        
        ClientDAO dao = new ClientDAO();
        return dao.getClient(courriel).getMotpasse();
    }
    
    public static void main(String[] args) {
        ClientCtrl pass = new ClientCtrl();
        String password = pass.getPassword("RobertBSutton56@yahoo.com");
        System.out.println(password);
    }
}
