package com.mycompany.loginandregistration;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author pfulu
 */
public class QuickChat {

    // ---- Inner Class ----
    static class Message {

        // Variables
        private String messageID;
        private String recipient;
        private String message;
        private String messageHash;
        private static int numMessages = 0;
        private static ArrayList<String> sentMessages = new ArrayList<>();

        // Constructor
        public Message(String recipient, String message) {
            this.recipient = recipient;
            this.message = message;
            this.messageID = String.valueOf((int)(Math.random() * 1000000000));
            numMessages++;
        }

        // Check message length
        public boolean checkMessageLength() {
            return message.length() <= 250; 
        }

        // Check recipient cell number
        public boolean checkRecipientCell() {
            return recipient.startsWith("+") && recipient.length() >= 10;
        }

        // Create message hash
        public String createMessageHash() {
            String[] words = message.split(" ");
            String firstWord = words[0];
            String lastWord = words[words.length - 1];
            String firstTwoID = messageID.substring(0, 2);
            messageHash = (firstTwoID + ":" + numMessages + ":" + firstWord + lastWord).toUpperCase();
            return messageHash;
        }

        // Send, disregard or store
        public String sentMessage(Scanner input) {
            System.out.println("\n1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");
            System.out.println("Choose: ");
            int choice = Integer.parseInt(input.nextLine());

            if (choice == 1) {
                sentMessages.add(" - ID: " + messageID + " - Hash: " + messageHash + " - To: " + recipient + " - Message: " + message);
                return "Message successfully sent.";

            } else if (choice == 2) {
                return "Press 0 to delete the message.";

            } else {
                // ---- JSON STORAGE ----
                try {
                    String jsonMessage = "{\n" +
                        "  \"messageID\": \"" + messageID + "\",\n" +
                        "  \"recipient\": \"" + recipient + "\",\n" +
                        "  \"message\": \"" + message + "\",\n" +
                        "  \"hash\": \"" + messageHash + "\"\n" +
                        "}\n";

                    java.io.FileWriter file = new java.io.FileWriter("messages.json", true);
                    file.write(jsonMessage);
                    file.close();

                } catch (Exception e) {
                    System.out.println("Error storing message: " + e.getMessage());
                }
                return "Message successfully stored.";
            }
        }

        // Get sent status for testing
        public String getSentStatus(int choice) {
            if (choice == 1) {
                sentMessages.add(" - ID: " + messageID + " - Hash: " + messageHash + " - To: " + recipient + " - Message: " + message);
                return "Message successfully sent.";
            } else if (choice == 2) {
                return "Press 0 to delete the message.";
            } else {
                return "Message successfully stored.";
            }
        }

        // Print all messages
        public static String printMessages() {
            if (sentMessages.isEmpty()) {
                return "No messages sent.";
            }
            String result = "";
            for (String msg : sentMessages) {
                result += msg + "\n";
            }
            return result;
        }

        // Return total messages
        public static int returnTotalMessages() {
            return numMessages;
        }

        // Getters
        public String getMessageID() {
            return messageID;
        }

        public String getMessageHash() {
            return messageHash;
        }

    }
    // ---- End of Inner Class ----


    // ---- Main Method ----
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ---- REGISTRATION --
        System.out.println("Please register first.");
        System.out.print("Enter username: ");
        String regUsername = input.nextLine();
        System.out.print("Enter password: ");
        String regPassword = input.nextLine();

        // ---LOGIN ----
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Please log in to access QuickChat.");
            System.out.print("Enter username: ");
            String loginUsername = input.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = input.nextLine();

            loggedIn = LoginandRegistration.LoginUser(regUsername, regPassword, loginUsername, loginPassword);

            if (!loggedIn) {
                System.out.println("Login failed. Incorrect username or password. Try again.");
            }
        }

        // --- WELCOME ---
        System.out.println("\nWelcome to QuickChat.");

        //  ---MENU----
        int choice = 0;
        while (choice != 3) {

            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose: ");
            choice = Integer.parseInt(input.nextLine());

            if (choice == 1) {

                System.out.print("How many messages do you want to send? ");
                int numMessages = Integer.parseInt(input.nextLine());

                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\n--- Message " + (i + 1) + " ---");

                    System.out.print("Enter recipient number: ");
                    String recipient = input.nextLine();

                    System.out.print("Enter message: ");
                    String message = input.nextLine();

                    // Create Message object
                    Message msg = new Message(recipient, message);

                    if (!msg.checkRecipientCell()) {
                        System.out.println("Cell number incorrectly formatted or does not contain international code. Please correct the number and try again.");
                    } else if (!msg.checkMessageLength()) {
                        int over = message.length() - 250;
                        System.out.println("Message exceeds 250 characters by " + over + "; please reduce the size.");
                    } else {
                        System.out.println("Message ID generated: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.createMessageHash());
                        System.out.println(msg.sentMessage(input));

                        System.out.println("\n--- Message Details ---");
                        System.out.println("Message ID: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("Recipient: " + recipient);
                        System.out.println("Message: " + message);
                    }
                }

                System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());

            } else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                System.out.println("Goodbye!");
            }
        }

        input.close();
    }
}
