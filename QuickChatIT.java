package com.mycompany.loginandregistration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author pfulu
 */
public class QuickChatIT {

    public QuickChatIT() {
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

    // ----- Message length success-----
    @Test
    public void testMessageLengthSuccess() {
        QuickChat.Message msg = new QuickChat.Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageLength());
    }

    // ----- Message length failure ------
    @Test
    public void testMessageLengthFailure() {
        String longMessage = "A".repeat(251);
        QuickChat.Message msg = new QuickChat.Message("+27718693002", longMessage);
        assertFalse(msg.checkMessageLength());
    }

    // ----- Recipient correctly formatted ----
    @Test
    public void testRecipientSuccess() {
        QuickChat.Message msg = new QuickChat.Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkRecipientCell());
    }

    // ----- Recipient incorrectly formatted ------
    @Test
    public void testRecipientFailure() {
        QuickChat.Message msg = new QuickChat.Message("08575975889", "Hi Keegan, did you receive the payment?");
        assertFalse(msg.checkRecipientCell());
    }

    // ---- Message hash correct-----
    @Test
    public void testMessageHash() {
        QuickChat.Message msg = new QuickChat.Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createMessageHash();
        assertTrue(hash.contains("HI") && hash.contains("TONIGHT?"));
    }

    // ---- Message ID created ---
    @Test
    public void testMessageIDCreated() {
        QuickChat.Message msg = new QuickChat.Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertNotNull(msg.getMessageID());
    }

    // ----- Message sent - Send ----
    @Test
    public void testMessageSentSend() {
        QuickChat.Message msg = new QuickChat.Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg.createMessageHash();
        assertEquals("Message successfully sent.", msg.getSentStatus(1));
    }

    // ---- Message sent - Disregard ----
    @Test
    public void testMessageSentDisregard() {
        QuickChat.Message msg = new QuickChat.Message("08575975889", "Hi Keegan, did you receive the payment?");
        msg.createMessageHash();
        assertEquals("Press 0 to delete the message.", msg.getSentStatus(2));
    }

    // ----  Message sent - Store ----
    @Test
    public void testMessageSentStore() {
        QuickChat.Message msg = new QuickChat.Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg.createMessageHash();
        assertEquals("Message successfully stored.", msg.getSentStatus(3));
    }
}