/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poeapk;

/**
 *
 * @author Ncedolwenkosi.
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

        // REGISTRATION
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
        System.out.println("Phone number sauccessfully captured.");

        String regResult = loginSystem.registerUser(userName, password, phoneNumber);
        System.out.println(regResult);

        // LOGIN (only if registration successful)
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
        System.out.println("Welcome to QuickChat.");
        System.out.println("\\n== Part 2: Messaging Menu Initialized ==");
        System.out.print("Please define the maximum messege limit size.");
        int totalLimit = input.nextInt();
        input.nextLine();
        
        Message[] chatDatabase = new Message[totalLimit];
        int messageCountTracker = 0;
        boolean processing = true;
        
        while (processing) {
            System.out.println("\\n=== MAIN MENU ===");
            System.out.println("1) Send messages.");
            System.out.println("2) Show recently sent messages. (Coming soon)");
            System.out.println("3) Quit");
            System.out.println("Selection: ");
            
            int menuSelection = input.nextInt();
            input.nextLine();
            
            switch (menuSelection){
               case 1:
                    // CRITICAL FIX: Explicit check if the tracking index has reached the user-defined limit array size
                    if (messageCountTracker >= totalLimit) {
                        System.out.println("\n[SYSTEM ALERT]: Maximum text send limit of " + totalLimit + " messages has been reached. Cannot add more.");
                        break;
                    }

                    System.out.println("\n--- Compose New Message ---");
                    System.out.print("Enter recipient cell number (e.g., +2781234567): ");
                    String targetRecipient = input.nextLine();

                    System.out.print("Enter text contents (Max 250 characters): ");
                    String rawContent = input.nextLine();

                    // Instantiating message object using tracking counter (Starts at 0, incremented dynamically)
                    Message draft = new Message(messageCountTracker, targetRecipient, rawContent);

                    // Validation processing
                    if (!draft.checkRecipientCell()) {
                        System.out.println("Validation Error: Cell phone number is incorrectly formatted or missing country code.");
                        break;
                    }

                    String limitResult = draft.checkMessageLength();
                    if (!limitResult.equals("Message ready to send.")) {
                        System.out.println("Validation Error: " + limitResult);
                        break;
                    }

                    // CRITICAL FIX: Output the exact success capture format required by the brief
                    System.out.println("\nMessage " + messageCountTracker + " successfully captured.");

                    // Execution configuration decision menu
                    System.out.println("Select Delivery Handling Action:");
                    System.out.println("1 - Send Now");
                    System.out.println("2 - Delete Message");
                    System.out.println("3 - Store to Send Later");
                    System.out.print("Selection: ");
                    int decision = input.nextInt();
                    input.nextLine(); // Clear buffer

                    // Outputs exact string from table ("Message status: Sent" etc.)
                    System.out.println(draft.sentMessage(decision));

                    // If user selects Option 1 (Sent) or Option 3 (Stored), log it to data structures array
                    if (decision == 1 || decision == 3) {
                        chatDatabase[messageCountTracker] = draft;
                        messageCountTracker++; // Move to next free index position safely
                    } else {
                        System.out.println("Message tracking dropped. Storage slot preserved.");
                    }
                    break;
                case 2: 
                    System.out.println("\\nExiting. Compilation Transmission Summary Report:");
                    System.out.println("==========================================================");
                    for (int i = 0; i < messageCountTracker; i++){
                        if (chatDatabase[i] != null){
                            System.out.println(chatDatabase[i].printMessageDetails());
                    }
                }
                    System.out.println("Total operations messages processed: " + messageCountTracker);
                    processing = false;
                    break;
                    
                default:
                    System.out.println("Invalid menu option.");
                    break;
                    }
                 }
        input.close();
    }
}