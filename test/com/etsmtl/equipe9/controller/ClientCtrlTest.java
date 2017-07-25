package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.ClientDAO;
import com.etsmtl.equipe9.model.Client;
import com.etsmtl.equipe9.service.DAOFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientCtrlTest {
    
    public ClientCtrlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ClientCtrl ctrl = new ClientCtrl();
        ClientDAO dao = DAOFactory.getInstance().getClientDAO();
        String password = ctrl.hash.get_SHA_256_SecurePassword("123456");
        Client testClient = new Client("test@test.com", "test", "test", password, "819-329-8475", new GregorianCalendar(1990, Calendar.FEBRUARY, 11).getTime());
        dao.insert(testClient);
    }
    
    @AfterClass
    public static void tearDownClass() {
        ClientCtrl ctrl = new ClientCtrl();
        ClientDAO dao = DAOFactory.getInstance().getClientDAO();
        Client testClient = ctrl.getClient("test@test.com");
        dao.delete(testClient);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkPassword method, of class ClientCtrl.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String courriel = "test@test.com";
        String motDePasse = "123456";
        ClientCtrl instance = new ClientCtrl();
        boolean expResult = true;
        boolean result = instance.checkPassword(courriel, motDePasse);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClient method, of class ClientCtrl.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        String courriel = "test@test.com";
        ClientCtrl instance = new ClientCtrl();
        String expResult = "test@test.com";
        String result = instance.getClient(courriel).getCourriel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllClient method, of class ClientCtrl.
     */
    @Test
    public void testGetAllClient() {
        System.out.println("getAllClient");
        ClientCtrl instance = new ClientCtrl();
        List<Client> result = instance.getAllClient();
        assertNotNull(result);
    }

    /**
     * Test of updateClient method, of class ClientCtrl.
     */
    @Test
    public void testUpdateClient() {
        System.out.println("updateClient");
        ClientCtrl instance = new ClientCtrl();
        Client client = instance.getClient("test@test.com");
        client.setNom("test2");
        instance.updateClient(client);
        Client updatedClient = instance.getClient("test@test.com");
        String expResult = "test2";
        String result = updatedClient.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateClientMotPasse method, of class ClientCtrl.
     */
    @Test
    public void testUpdateClientMotPasse() {
        System.out.println("updateClientMotPasse");
        ClientCtrl instance = new ClientCtrl();
        Client client = instance.getClient("test@test.com");
        String newPassword = "654321";
        instance.updateClientMotPasse(client, newPassword);
        Client updatedClient = instance.getClient("test@test.com");
        String expResult = instance.hash.get_SHA_256_SecurePassword("654321");
        String result = updatedClient.getMotpasse();
        assertEquals(expResult, result);
    }
    
}
