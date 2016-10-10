/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;



/**
 *
 * @author Nicolas Desktop
 */
public class LoginCtrl {
    
    public String getPassword (String courriel){
        
        ClientDAO dao = new ClientDAO();
        return dao.getClient(courriel).getMotpasse();
    }
    
    public static void main(String[] args) {
        LoginCtrl login = new LoginCtrl();
        String password = login.getPassword("RobertBSutton56@yahoo.com");
        System.out.println("Le password est: "+password);
    }
}
