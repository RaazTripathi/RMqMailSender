package com.laitkor.activemq.client;

//import com.mednet.bean.Email;
//import com.mednet.util.NewHibernateUtil;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.http.HTTPException;

import org.xml.sax.InputSource;

import com.laitkor.activemq.bean.Email;
//import org.apache.log4j.Logger;
//import org.hibernate.SessionFactory;

/**
*
* @author Raaz
*/
public class SaveInMessageQueue {
  String smsId="";
//  private static Logger logger = org.apache.log4j.Logger.getLogger("logger"); 
 
//  public static SessionFactory factory1;    
  public SaveInMessageQueue(){
       try{             
  //         factory1 = NewHibernateUtil.getSessionFactory();
       }catch(HTTPException e){
    //      logger.debug(e);
                   
       }
   }
 
 public String getInQueue(Email msg){ 
      String returnContent="";
      
         try {
                  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                   SimpleDateFormat dateFormat1=new SimpleDateFormat("hh:mm:ss");
                    Date date = new Date();
                     Date rqstDate = dateFormat.parse(dateFormat.format(date));
                      Date rqstTime = dateFormat1.parse(dateFormat1.format(date));
                          
                        /*//-- un marshling  --//
                         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                           JAXBContext jaxbContext = JAXBContext.newInstance(Email.class); 
                            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
                             Email msg = (Email) jaxbUnmarshaller.unmarshal(dBuilder.parse(new InputSource(new StringReader(cust_info)))); 
                              System.out.println("un-marshling done....");
                               */
                      JAXBContext jaxbContext = JAXBContext.newInstance(Email.class); 

                                       //-- marshling for save message in queue--//  
                                         Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); 
                                          jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                                           StringWriter sw = new StringWriter();
                                            jaxbMarshaller.marshal(msg, sw); 
                                               
                                             Publisher pb = new Publisher(sw.toString());                                              
                                              pb.run();                  
      //                                         logger.info("message sent in active mq..");   
                                   System.out.println(msg.getSubject());
                  returnContent = "successfully saved in database using web service...";
	       } catch(Exception e){ 
	    	   
	    	   e.printStackTrace();
        //          logger.info("unmashling not done----");
          //        logger.debug(e);
             }
     return returnContent;
   }
  
}
