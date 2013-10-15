package com.laitkor.activemq.connection;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author Raaz
 */
public class QueueConnection {
    public static String subject = "TESTQUEUE";
  // private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("logger");
   
    private static void startBroker() throws Exception {
    //    logger.info("Starting Broker");
        BrokerService broker = new BrokerService();
        broker.setUseJmx(true);
        broker.addConnector("tcp://localhost:61616");
        broker.start();
      //  logger.info("Broker started");
    }  
    

    /**
     * Use the ActiveMQConnectionFactory to get a JMS ConnectionFactory. In an
     * enterprise application this would normally be accessed through JNDI.
     */
    public static ConnectionFactory getJmsConnectionFactory()
            throws JMSException {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        return new ActiveMQConnectionFactory(user, password, url);
    }
}
