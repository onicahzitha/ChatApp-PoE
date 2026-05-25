/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apkpoe;
        import java.util.Random;
/**
 *
 * @author Ncedolwenkosi
 */
public class Message {
    // Declarations 
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
    
    //Confirm message ID is not more than 10 characters long
    public boolean checkMessageID(){
        return this.messageID != null && this.messageID.length() <=10;
    }
    //Confirm text length is less than 250 characters
    public String checkMessageLegnth(){
        if (this.messageContent == null){
            return "Please enter your message.";
        }
        if (this.messageContent.length() >250){
            int overflow = this.messageContent.length() -250;
            return "Message exceeds 250 characters by" + overflow + "characters; please reduce the size.";
        }
        return "Message ready to send.";
    }
    //Reuse country code and length logic from
    public boolean checkRecipientCell(){
        if (this.recipientNumber == null) return false;
        return this.recipientNumber.matches("^\\\\+\\\\d{1,10}$");
    }
    public String createMessageHash(){
        if (this.messageID == null || this.messageID.length() <2 || this.messageContent == null || this.messageContent.trim().isEmpty()){
        return "00:0EMPTY";
    }
        //First two digits of textID
        String idPrefix = this.messageID.substring(0, 2);
       
        //Clean text boundaries
        String cleanText = this.messageContent.trim().replaceAll("[^a-zA-Z0-9\\\\s]", "");
        String[] words = cleanText.split("\\s+");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        //Assemble and capitalize string formatting matching specifications
        String combinedHash = idPrefix + ";" + this.messageNumber + firstWord + lastWord;
        return combinedHash.toUpperCase();
    }
    
    //Complete switch casing
    public String sentMessage(int choice){
        switch (choice){
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Unknown status action selection.";
        }
        }
     //Formats total systsem values for sequential standard array prints
    public String printMessageDetails(){
        return "MessageID: " + this.messageID + "\n" +
                "Message Hash: "+ this.messageHash + "\n" +
                "Message: " + this.messageContent + "\n" +
                "------------------------------------";
        
    }
    
    //Getters for structural tracking
    public String getMessageID(){return messageID;}
    public String getMessageHash(){return messageHash;}
    public String getMessageNumber (){return recipientNumber;}
    public String getMessageContent(){return messageContent;}

}
