/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeapk;
import java.util.Random;
/**
 *
 * @author Ncedolwenkosi.
 */
public class Message {
    //Declarations
    private String messageID;
    private int messageNumber;
    private String recipientNumber;
    private String messageContent;
    private String messageHash;
    
    //Initializing
    public Message(int messageNumber, String recipientNumber, String messageContent){
        this.messageNumber = messageNumber;
        this.recipientNumber = recipientNumber;
        this.messageContent = messageContent;

    // Auto-running the generator logic upon creation
    this.messageID = generateMessageID();
    this.messageHash = createMessageHash();
}
    //Generate a digit number for tracking
    private String generateMessageID(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++){
            sb.append(random.nextInt(10));
    }
        return sb.toString();
    }
    
    //Confirm message ID is not more than 10 dcharacters long
    public boolean checkMessageID(){
        return this.messageID != null && this.messageID.length() <= 10;
    }
    
    //Confrim text length is less than 250 characters
    public String checkMessageLength(){
        if (this.messageContent == null){
            return "Please enter your messge.";
    }
        if (this.messageContent.length() > 250){
            int overflow = this.messageContent.length() - 250;
            return "Message exceeds 250 characters by" + overflow + "characters; please reduce the size.";
    }
        return "Message ready to send.";
    }
    
    //Reuse country code and length logic from
public boolean checkRecipientCell() {
    if (this.recipientNumber == null) {
        return false;
    }
    // Verifies it starts with a '+' and contains only digits afterwards
    if (!this.recipientNumber.startsWith("+")) {
        return false;
    }
    
    // Limit digits to 12
    return this.recipientNumber.length() <= 12;
}
    
    public String createMessageHash(){
        if (this.messageID == null || this.messageID.length() <2 || this.messageContent == null || this.messageContent.trim().isEmpty()){
            return "00:0:EMPTY";
        }
    
        //First two digits of textID
        String idPrefix = this.messageID.substring(0, 2);
      
        //Clean text boundaries
        String cleanText = this.messageContent.trim().replaceAll("[^a-zA-Z0-9\\\\s]", "");
        String[] words = cleanText.split("\\s+");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        //Assemble and Capitalize string formatting matching specifications
    // 3. Glue them together with colons and force it to be CAPITALIZED
    return (idPrefix + ":" + this.messageNumber + ":" + firstWord + lastWord).toUpperCase();
}
    
    //Complete switch casing
    public String sentMessage(int choice){
    switch (choice){
        case 1:
            return "Message status: Sent";
        case 2:
            return "Message status; Deleted";
        case 3:
            return "Message status: Stored";
        default:
            return "Unknown status option selection.";
        }
    }
    
    //Formats total system data values for sequential standard array prints
      public String printMessageDetails() {
        return "Message ID: " + this.messageID + "\n" +
               "Message Hash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipientNumber + "\n" +
               "Message: " + this.messageContent;
        }
       
}