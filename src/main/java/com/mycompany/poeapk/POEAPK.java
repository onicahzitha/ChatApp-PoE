/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poeapk;

/**
 *
 * @author Ncedolwenkosi Onicah Zitha
 */
import java.util.Scanner;

public class POEAPK {

    public static void main(String[] args) {
        //Creating a Scanner object
        Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();

        // DECLARATIONS
        String firstName;
        String lastName;
        String userName;
        String password;
        String phoneNumber;
        String loginUser;
        String loginPass;

        System.out.println("--- User Registration ---");
        System.out.print("Enter First Name: ");
        firstName = input.nextLine();
        System.out.println("First name successfully captured.");
        System.out.print("Enter Last Name: ");
        lastName = input.nextLine();
        System.out.println("Last name successfully captured.");
        System.out.print("Enter Username (must contain '_' and be <= 5 ):");
        userName = input.nextLine();
        System.out.println("Username successfully captured.");
        System.out.print("Enter Password: (>= 8 characters, 1 uppercase, 1 lowercase, 1 special character.)");
        password = input.nextLine();
        System.out.println("Password successfully captured.");
        System.out.print("Enter SA Phone (+27...): ");
        phoneNumber = input.nextLine();
        System.out.println("Phone number successfully captured.");

        String regResult = loginSystem.registerUser(userName, password, phoneNumber);
        System.out.println(regResult);

        if (regResult.contains("successfully captured")) {
            System.out.println("\n--- User Login ---");
            System.out.print("Enter Username: ");
            loginUser = input.nextLine();
            System.out.print("Enter Password: ");
            loginPass = input.nextLine();

            boolean isSuccess = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(
                    loginSystem.returnLoginStatus(isSuccess, firstName, lastName)
            );

            System.out.println("\nType 'YES' to confirm you have read everything:");
            String confirm = input.nextLine();

            if (confirm.equalsIgnoreCase("YES")) {
                System.out.println("Welcome " + firstName + " " + lastName + " , it is great to see you again..");
            } else {
                System.out.println("Access denied. Please read the instructions first.");
            }
        }
        System.out.println("\nWelcome to QuickChat.");
        System.out.println("== Part 2: Messaging Menu Initialized ==");
        System.out.print("Please define the maximum message limit size: ");
        int totalLimit = input.nextInt();
        input.nextLine();

        Message[] chatDatabase = new Message[totalLimit];
        int messageCountTracker = 0;
        boolean processing = true;

        String[] sentMessage = new String[totalLimit];
        String[] disregardedMessages = new String[totalLimit];
        String[] storedMessages = new String[totalLimit];
        String[] messageHashArray = new String[totalLimit];
        String[] messageIDArray = new String[totalLimit];

        while (processing) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1) Send messages.");
            System.out.println("2) Show recently sent messages.");
            System.out.println("3) Stored Messages Submenu");
            System.out.println("4) Quit");
            System.out.print("Selection: ");

            int menuSelection = input.nextInt();
            input.nextLine();

            switch (menuSelection) {
                case 1:
                    if (messageCountTracker >= totalLimit) {
                        System.out.println("\n[SYSTEM ALERT]: Maximum text send limit of " + totalLimit + " messages has been reached.");
                        break;
                    }

                    System.out.println("\n--- Compose New Message ---");
                    System.out.print("Enter recipient cell number (e.g., +2781234567): ");
                    String targetRecipient = input.nextLine();

                    System.out.print("Enter text contents (Max 250 characters): ");
                    String rawContent = input.nextLine();

                    Message draft = new Message(messageCountTracker, targetRecipient, rawContent);

                    if (!draft.checkRecipientCell()) {
                        System.out.println("Validation Error: Cell phone number is incorrectly formatted.");
                        break;
                    }

                    String limitResult = draft.checkMessageLength();
                    if (!limitResult.equals("Message ready to send.")) {
                        System.out.println("Validation Error: " + limitResult);
                        break;
                    }

                    System.out.println("\nMessage " + messageCountTracker + " successfully captured.");
                    System.out.println("Select Delivery Handling Action:");
                    System.out.println("1 - Send Now");
                    System.out.println("2 - Delete Message");
                    System.out.println("3 - Store to Send Later");
                    System.out.print("Selection: ");
                    int decision = input.nextInt();
                    input.nextLine();

                    System.out.println(draft.sentMessage(decision));
                    messageHashArray[messageCountTracker] = draft.getMessageHash();
                    messageIDArray[messageCountTracker] = draft.getMessageID();

                    if (decision == 1) {
                        sentMessage[messageCountTracker] = draft.getMessageContent();
                        chatDatabase[messageCountTracker] = draft;
                        messageCountTracker++;
                    } else if (decision == 2) {
                        disregardedMessages[messageCountTracker] = draft.getMessageContent();
                        messageCountTracker++;
                    } else if (decision == 2) {
                        disregardedMessages[messageCountTracker] = draft.getMessageContent();
                        messageCountTracker++;
                    }
                    break;
                case 2:
                    System.out.println("\nShow recently sent messages feature initialized:");
                    System.out.println("==========================================================");
                    for (int i = 0; i < messageCountTracker; i++) {
                        if (chatDatabase[i] != null) {
                            System.out.println(chatDatabase[i].printMessageDetails());
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;
                case 3:
                    boolean inSubmenu = true;
                    while (inSubmenu) {
                        System.out.println("\n--- Stored Messages Task Report ---");
                        System.out.println("a) Display the sender and recipient of all stored messages.");
                        System.out.println("b) Display the longest stored message.");
                        System.out.println("c) Search for a message ID.");
                        System.out.println("d) Search for all messages stored for a particular recipient.");
                        System.out.println("e) Delete a message using the message hash.");
                        System.out.println("f) Display a report of all stored messages.");
                        System.out.println("g) Return to Main Menu");
                        System.out.print("Select report option: ");
                        String subChoice = input.nextLine().toLowerCase();

                        switch (subChoice) {
                            case "a":
                                System.out.println("\n--- Contact List: All Stored Messages ---");
                                boolean foundA = false;
                                for (int i = 0; i < messageCountTracker; i++) {
                                    if (storedMessages[i] != null && chatDatabase[i] != null) {
                                        System.out.println("-> Recipient Cell: " + chatDatabase[i].getRecipientNumber());
                                        System.out.println("   Message Text: " + storedMessages[i]);
                                        System.out.println("----------------------------------------");
                                        foundA = true;
                                    }
                                }
                                if (!foundA) {
                                    System.out.println("No messages currently flagged as Stored.");
                                }
                                break;
                            case "b":
                                System.out.println("\n--- Longest Stored Message Profile ---");
                                int longestIndex = -1;
                                int maxLength = -1;
                                for (int i = 0; i < messageCountTracker; i++) {
                                    if (storedMessages[i] != null) {
                                        if (storedMessages[i].length() > maxLength) {
                                            maxLength = storedMessages[i].length();
                                            longestIndex = i;
                                        }
                                    }
                                }

                                if (longestIndex != -1 && chatDatabase[longestIndex] != null) {
                                    System.out.println("Recipient Number: " + chatDatabase[longestIndex].getRecipientNumber());
                                    System.out.println("Message Content: " + storedMessages[longestIndex]);
                                    System.out.println("Character Length: " + maxLength);
                                } else {
                                    System.out.println("No stored messages found to analyze.");
                                }
                                break;
                            case "c":
                                System.out.print("\nEnter 10-digit Message ID to search: ");
                                String searchID = input.nextLine().trim();
                                boolean foundC = false;

                                for (int i = 0; i < messageCountTracker; i++) {
                                    if (messageIDArray[i] != null && messageIDArray[i].equals(searchID)) {
                                        if (chatDatabase[i] != null) {
                                            System.out.println("\n[Match Found Successfully]:");
                                            System.out.println("Recipient Number: " + chatDatabase[i].getRecipientNumber());
                                            System.out.println("Message Contents: " + chatDatabase[i].getMessageContent());
                                            System.out.println("Hash ID: " + messageHashArray[i]);
                                        } else {
                                            System.out.println("\nID found but message metadata was disregarded/deleted.");
                                            System.out.println("Logged Content: " + disregardedMessages[i]);
                                        }
                                        foundC = true;
                                        break;
                                    }
                                }
                                if (!foundC) {
                                    System.out.println("Error: Message ID '" + searchID + "' does not exist in the repository.");
                                }
                                break;
                            case "d":
                                System.out.print("\nEnter recipient cell number to filter (e.g. +27...): ");
                                String searchRecipient = input.nextLine().trim();
                                boolean foundD = false;
                                int matchCounter = 1;

                                System.out.println("\n--- Sequential Transmission Log for: " + searchRecipient + " ---");
                                for (int i = 0; i < messageCountTracker; i++) {
                                    if (chatDatabase[i] != null && chatDatabase[i].getRecipientNumber().equals(searchRecipient)) {
                                        System.out.println("Record #" + matchCounter);
                                        System.out.println("Message ID: " + messageIDArray[i]);
                                        System.out.println("Message Text: " + chatDatabase[i].getMessageContent());
                                        System.out.println("Hash String: " + messageHashArray[i]);
                                        System.out.println("----------------------------------------");
                                        matchCounter++;
                                        foundD = true;
                                    }
                                }
                                if (!foundD) {
                                    System.out.println("No transactional logs captured for cell: " + searchRecipient);
                                }
                                break;
                            case "e":
                                System.out.print("\nEnter Message Hash key string to clear item: ");
                                String targetHash = input.nextLine().trim().toUpperCase();
                                boolean foundE = false;

                                for (int i = 0; i < messageCountTracker; i++) {
                                    if (messageHashArray[i] != null && messageHashArray[i].equals(targetHash)) {
                                        System.out.println("\nRemoving message record with Hash ID: " + targetHash);

                                        if (storedMessages[i] != null) {
                                            disregardedMessages[i] = storedMessages[i];
                                            storedMessages[i] = null;
                                        } else if (sentMessage[i] != null) {
                                            disregardedMessages[i] = sentMessage[i];
                                            sentMessage[i] = null;
                                        }

                                        chatDatabase[i] = null;
                                        messageHashArray[i] = "[DELETED]";
                                        messageIDArray[i] = "[DELETED]";

                                        System.out.println("System Operation Status: Message successfully deleted.");
                                        foundE = true;
                                        break;
                                    }
                                }
                                if (!foundE) {
                                    System.out.println("Deletion Failed: Provided Hash key could not be located.");
                                }
                                break;
                            case "f":
                                System.out.println("\n=======================================================");
                                System.out.println("          QUICKCHAT SYSTEM MASTER TRANSMISSION REPORT  ");
                                System.out.println("=======================================================");

                                if (messageCountTracker == 0) {
                                    System.out.println("No records captured across current runtime instance.");
                                } else {
                                    for (int i = 0; i < messageCountTracker; i++) {
                                        System.out.println("INDEX ROW SLOT [" + i + "]");
                                        System.out.println("Message ID   : " + messageIDArray[i]);
                                        System.out.println("Message Hash : " + messageHashArray[i]);

                                        if (chatDatabase[i] != null) {
                                            System.out.println("Recipient Phone : " + chatDatabase[i].getRecipientNumber());
                                            System.out.println("Active Text  : " + chatDatabase[i].getMessageContent());
                                        } else {
                                            System.out.println("Status Block : [RECORD DISREGARDED / PURGED]");
                                            if (disregardedMessages[i] != null) {
                                                System.out.println("Purged Text Content: " + disregardedMessages[i]);
                                            }
                                        }
                                        System.out.println("-------------------------------------------------------");
                                    }
                                }
                                break;
                            case "g":
                                System.out.println("Returning to Main Menu...");
                                inSubmenu = false;
                                break;
                            default:
                                System.out.println("Invalid reporting selection.");
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nExiting. Compilation Transmission Summary Report:");
                    System.out.println("========================================");
                    for (int i = 0; i < messageCountTracker; i++) {
                        if (chatDatabase[i] != null) {
                            System.out.println(chatDatabase[i].printMessageDetails());
                        }
                    }
                    System.out.println("Total operational messages processed: " + messageCountTracker);
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    processing = false;
                    break;
                default:
                    System.out.println("Invalid menu option selection. Please try again.");
                    break;
            }
        }
        input.close();
    }
}
