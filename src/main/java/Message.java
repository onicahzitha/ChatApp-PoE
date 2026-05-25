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
        //this.messageHash = generateMessageHash();
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
    
        }
    }

}
