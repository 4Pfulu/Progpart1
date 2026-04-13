/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.loginandregistration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LoginandRegistrationIT {
    
    public LoginandRegistrationIT() {
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

    // ---------Username Test-----------

    @Test
    public void testCheckUsername() {
        // "kyl_1" has underscore, 5 chars or less = true
        assertTrue(LoginandRegistration.checkUsername("kyl_1"));
    }

    @Test
    public void testCheckUsername_IncorrectlyFormatted() {
        // "kyle!!!!!!!" no underscore, too long = false
        assertFalse(LoginandRegistration.checkUsername("kyle!!!!!!!"));
    }

    // ---------password Test------------

    @Test
    public void testCheckPasswordComplexity() {
        // "Ch&&sec@ke99!" has capital, number, special char, 8+ chars = true
        assertTrue(LoginandRegistration.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_DoesNotMeetRequirements() {
        // "password" no capital, no number, no special char = false
        assertFalse(LoginandRegistration.checkPasswordComplexity("password"));
    }

    // -----------CellPhone Test------------

    @Test
    public void testCheckCellPhoneNUmber() {
        // "+27838968976" has international code = true
        assertTrue(LoginandRegistration.checkCellPhoneNUmber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNUmber_IncorrectlyFormatted() {
        // "08966553" no international code = false
        assertFalse(LoginandRegistration.checkCellPhoneNUmber("08966553"));
    }

    // --------Login Test----------

    @Test
    public void testLoginUser() {
        // Correct username and password match = true
        assertTrue(LoginandRegistration.LoginUser("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Failed() {
        // Wrong credentials = false
        assertFalse(LoginandRegistration.LoginUser("kyl_1", "Ch&&sec@ke99!", "kyl_1", "wrongpassword"));
    }

    // ---------Login Status check----------

    @Test
    public void testReturnLoginStatus() {
        // Matching credentials = success message with first and last name
        String result = LoginandRegistration.returnLoginStatus("kyl_1", "Kyle", "Smith", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle,Smith it is great to see you.", result);
    }

    @Test
    public void testReturnLoginStatus_Failed() {
        // Wrong credentials = failed message
        String result = LoginandRegistration.returnLoginStatus("kyl_1", "Kyle", "Smith", "Ch&&sec@ke99!", "kyl_1", "wrongpassword");
        assertEquals("Username or Password incorrect please try again.", result);
    }
  }
