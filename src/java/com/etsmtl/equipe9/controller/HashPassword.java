/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oli
 */
public class HashPassword {
    
    public String salt = "gg";
        
    public String get_SHA_256_SecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            generatedPassword = new String(md.digest(passwordToHash.getBytes("UTF-8")));
            System.out.println("HashPassword : "+ generatedPassword);
        } 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Logger.getLogger(HashPassword.class.getName()).log(Level.SEVERE, null, e);
        }
        return generatedPassword;
    }
}
