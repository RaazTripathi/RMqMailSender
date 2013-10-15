package com.laitkor.activemq.server;


import com.laitkor.activemq.bean.*;
import com.laitkor.activemq.connection.QueueConnection;
import com.laitkor.activemq.service.SendEmail;

import java.io.StringReader;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

/**
 *
 * @author Raaz
 */
public class MessageQueueManager extends Thread {
    private static Logger logger = Logger.getLogger("logger");
    
    boolean flag = true;
    ConnectionFactory factory = null;
    Connection connection = null;
    Session session = null;
    Destination destination = null;
    MessageConsumer consumer = null;
    
    MessageQueueManager(){}
    
    public void run() { 
        try{
              factory = QueueConnection.getJmsConnectionFactory();
               connection = factory.createConnection();
                connection.start();
                 session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                  destination = session.createQueue(QueueConnection.subject);
                    consumer = session.createConsumer(destination);
                      //System.out.println("read message...");
                      logger.info("read message....");
            }catch(Exception e){
             e.printStackTrace();
             logger.debug(e);
             
            }
        
	while(flag){
            try{
                 Message message = consumer.receive();                              
                  if (message instanceof TextMessage) {
                   TextMessage textMessage = (TextMessage) message;                           
                    logger.info("received messages..."+textMessage.getText());
                     
                     //--un marshling for the message fetched from active mq --//
                      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        JAXBContext jaxbContext = JAXBContext.newInstance(Email.class); 
                         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
                         Email msg = (Email) jaxbUnmarshaller.unmarshal(dBuilder.parse(new InputSource(new StringReader(textMessage.getText()))));  
                          
                           //--- call class for generatin http request for sending mssg.
                           SendEmail sendMail = new SendEmail(msg);     
                          sendMail.start();
                                 
                 }         //-- if closed                     
               }catch(Exception e){                  
                  logger.debug(e);
                  flag = false;
                }
        }
        
        try{session.close();connection.close();}catch(JMSException e){logger.debug(e);}
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MessageQueueManager mqc = new MessageQueueManager();
        mqc.start();
    }
}
