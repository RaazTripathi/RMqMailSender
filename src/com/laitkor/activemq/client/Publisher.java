package com.laitkor.activemq.client;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.broker.BrokerService;
//import org.apache.log4j.Logger;

import com.laitkor.activemq.connection.QueueConnection;

/**
 *
 * @author Raaz
 */
public class Publisher extends Thread {
    String content = "";  
  //  private static Logger logger = org.apache.log4j.Logger.getLogger("logger");

    public Publisher(){
        super();
    }    
    public Publisher(String contentXML){
        super();
        content = contentXML;        
    }
    
    public void run() {
    Connection connection = null; 
        try {
                ConnectionFactory factory = QueueConnection.getJmsConnectionFactory();
                 connection = factory.createConnection();
                  connection.start();
                   Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
                    // Topic topic = session.createTopic(ExamplePublishAndSubscribe.TOPIC1);
                     Destination destination = session.createQueue(QueueConnection.subject);
                      MessageProducer producer = session.createProducer(destination);
                       producer.setTimeToLive(100);
                        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);                        

                          TextMessage message = session.createTextMessage(content);
                           producer.send(message);                                              // send message in active mq--//

                            connection.close(); 
                   
                    
           }catch (Exception e) {                
    //             logger.info("error in sending message....");
      //           logger.debug(e);
           } finally {
                if (connection != null) {
                    try {
                             connection.close();
                        } catch (JMSException e) { 
                            //logger.info("connection not closed successfully in publisher.java...");logger.debug(e);
                        }
                }
          }  
    }
    
    public void stopPublishing() throws Exception {
        BrokerService broker = new BrokerService();
        broker.stop();
    }
    
    
}

