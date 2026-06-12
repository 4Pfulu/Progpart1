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
        private static ArrayList<String> disregardedMessages = new ArrayList<>();
        private static ArrayList<String> storedMessages = new ArrayList<>();      
        private static ArrayList<String> messageHashes = new ArrayList<>();       
        private static ArrayList<String> messageIDs = new ArrayList<>();          

        // Constructor
        public Message(String recipient, String message) {
            this.recipient = recipient;
            this.message = message;
            this.messageID = String.valueOf((int)(Math.random() * 1000000000));
            numMessages++;
            messageIDs.add(messageID);
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
            messageHashes.add(messageHash);
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
                disregardedMessages.add("- ID: " + messageID + " -Hash:" + messageHash + " - To: " + recipient + "Message:" + message);
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
                    storedMessages.add(" - ID: " + messageID + " - Hash: " + messageHash + " - To: " + recipient + " - Message: " + message);

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
                disregardedMessages.add(" - ID: " + messageID + " - Hash: " + messageHash + " - To: " + recipient + " - Message: " + message);
                return "Press 0 to delete the message.";
            } else {
                storedMessages.add(" - ID: " + messageID + " - Hash: " + messageHash + " - To: " + recipient + " - Message: " + message);
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
        
          // Display all stored messages
        public static void displayStoredMessages() {
            if (storedMessages.isEmpty()) {
                System.out.println("No stored messages.");
            } else {
                System.out.println("\n--- Stored Messages ---");
                for (String msg : storedMessages) {
                    System.out.println(msg);
                }
            }
        }

        //  Display longest message
        public static void displayLongestMessage() {
            String longest = "";
            for (String msg : storedMessages) {
                if (msg.length() > longest.length()) {
                    longest = msg;
                }
            }
            if (longest.isEmpty()) {
                System.out.println("No stored messages.");
            } else {
                System.out.println("\nLongest message: " + longest);
            }
        }

        // Search by message ID
        public static void searchByMessageID(String searchID) {
            boolean found = false;
            for (String msg : storedMessages) {
                if (msg.contains(searchID)) {
                    System.out.println("Message found: " + msg);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No message found with ID: " + searchID);
            }
        }

        //  Search by recipient
        public static void searchByRecipient(String searchRecipient) {
            boolean found = false;
            for (String msg : storedMessages) {
                if (msg.contains(searchRecipient)) {
                    System.out.println(msg);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No messages found for: " + searchRecipient);
            }
        }

        //  Delete by message hash
        public static void deleteByHash(String hash) {
            boolean found = false;
            for (int i = 0; i < storedMessages.size(); i++) {
                if (storedMessages.get(i).contains(hash)) {
                    System.out.println("Message: \"" + storedMessages.get(i) + "\" successfully deleted.");
                    storedMessages.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No message found with hash: " + hash);
            }
        }

        //  Display full report
        public static void displayReport() {
            System.out.println("\n========== FULL REPORT ==========");

            System.out.println("\n--- Sent Messages ---");
            if (sentMessages.isEmpty()) {
                System.out.println("None.");
            } else {
                for (String msg : sentMessages) {
                    System.out.println(msg);
                }
            }

            System.out.println("\n--- Disregarded Messages ---");
            if (disregardedMessages.isEmpty()) {
                System.out.println("None.");
            } else {
                for (String msg : disregardedMessages) {
                    System.out.println(msg);
                }
            }

            System.out.println("\n--- Stored Messages ---");
            if (storedMessages.isEmpty()) {
                System.out.println("None.");
            } else {
                for (String msg : storedMessages) {
                    System.out.println(msg);
                }
            }

            System.out.println("\n--- Message Hashes ---");
            for (String hash : messageHashes) {
                System.out.println(hash);
            }

            System.out.println("\n--- Message IDs ---");
            for (String id : messageIDs) {
                System.out.println(id);
            }
            
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
            System.out.println("4) Stored Messages");
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
                break;
            } else if (choice == 4) { 

                int subChoice = 0;
                while (subChoice != 7) {
                    System.out.println("\n--- Stored Messages Menu ---");
                    System.out.println("1)Display all stored messages");
                    System.out.println("2)Display longest message");
                    System.out.println("3)Search by message ID");
                    System.out.println("4)Search by recipient");
                    System.out.println("5)Delete message by hash");
                    System.out.println("6)Display full report");
                    System.out.println("7)Back to main menu");
                    System.out.print("Choose: ");
                    subChoice = Integer.parseInt(input.nextLine());

                    if (subChoice == 1) {
                        
                        Message.displayStoredMessages();
                    } else if (subChoice == 2) {
                        
                        Message.displayLongestMessage();
                    } else if (subChoice == 3) {
                        
                        System.out.print("Enter message ID to search: ");
                        String searchID = input.nextLine();
                        Message.searchByMessageID(searchID);
                        
                    } else if (subChoice == 4) {
                        System.out.print("Enter recipient number to search: ");
                        String searchRecipient = input.nextLine();
                        Message.searchByRecipient(searchRecipient);
                        
                    } else if (subChoice == 5) {
                        System.out.print("Enter message hash to delete: ");
                        String hash = input.nextLine();
                        Message.deleteByHash(hash);
                        
                    } else if (subChoice == 6) {
                        Message.displayReport();
                        
                    } else if (subChoice == 7) {
                        System.out.println("Returning to main menu.");
                    }
                }
            }

        }

        input.close();
    }
}
