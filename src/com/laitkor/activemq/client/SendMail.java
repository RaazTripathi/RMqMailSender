package com.laitkor.activemq.client;

import com.laitkor.activemq.bean.Email;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raaz
 */
public class SendMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Email msg = new Email();
       msg.setSubject("hello");
       msg.setEmail("ram.tripathi@laitkor.com");
       msg.setMsgBody("Hello Raaz");
         SaveInMessageQueue inQueue = new SaveInMessageQueue();
            String result = inQueue.getInQueue(msg);
            
            System.out.print("task done");
    }
}
