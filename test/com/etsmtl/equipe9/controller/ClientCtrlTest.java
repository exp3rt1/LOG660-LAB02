package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.model.Client;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
    
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
*/
public class ClientCtrlTest {
    
    public ClientCtrlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPassword method, of class ClientCtrl.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String courriel = "";
        String motDePasse = "";
        ClientCtrl instance = new ClientCtrl();
        boolean expResult = false;
        boolean result = instance.getPassword(courriel, motDePasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClient method, of class ClientCtrl.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        String courriel = "";
        ClientCtrl instance = new ClientCtrl();
        Client expResult = null;
        Client result = instance.getClient(courriel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllClient method, of class ClientCtrl.
     */
    @Test
    public void testGetAllClient() {
        System.out.println("getAllClient");
        ClientCtrl instance = new ClientCtrl();
        List<Client> expResult = null;
        List<Client> result = instance.getAllClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClient method, of class ClientCtrl.
     */
    @Test
    public void testUpdateClient() {
        System.out.println("updateClient");
        Client client = null;
        ClientCtrl instance = new ClientCtrl();
        boolean expResult = false;
        boolean result = instance.updateClient(client);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClientMotPasse method, of class ClientCtrl.
     */
    @Test
    public void testUpdateClientMotPasse() {
        System.out.println("updateClientMotPasse");
        Client client = null;
        String newPassword = "";
        ClientCtrl instance = new ClientCtrl();
        boolean expResult = false;
        boolean result = instance.updateClientMotPasse(client, newPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
