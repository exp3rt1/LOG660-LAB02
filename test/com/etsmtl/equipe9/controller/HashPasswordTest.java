package com.etsmtl.equipe9.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashPasswordTest {
    
    public HashPasswordTest() {
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
     * Test of get_SHA_256_SecurePassword method, of class HashPassword.
     */
    @Test
    public void testGet_SHA_256_SecurePassword() {
        System.out.println("get_SHA_256_SecurePassword");
        String passwordToHash = "";
        HashPassword instance = new HashPassword();
        String expResult = "";
        String result = instance.get_SHA_256_SecurePassword(passwordToHash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
