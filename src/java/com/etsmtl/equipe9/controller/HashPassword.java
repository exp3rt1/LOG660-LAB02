package com.etsmtl.equipe9.controller;

import static java.nio.charset.StandardCharsets.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            md.update(salt.getBytes(UTF_8));
            
            byte[] hashedPassword = md.digest(passwordToHash.getBytes(UTF_8));
//            System.out.println("test : "+ new String(hashedPassword, UTF_8));
            
            generatedPassword = new String(hashedPassword, UTF_8);
//            System.out.println("HashPassword : "+ generatedPassword);
        } 
        catch (NoSuchAlgorithmException e) {
            Logger.getLogger(HashPassword.class.getName()).log(Level.SEVERE, null, e);
        }
        return generatedPassword;
    }
}
