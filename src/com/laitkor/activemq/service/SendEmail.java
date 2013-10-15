package com.laitkor.activemq.service;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.laitkor.activemq.bean.Email;

public class SendEmail  extends Thread
 {
	 private static final Log  log = LogFactory.getLog(SendEmail.class);
		private String mailhost ;
		private String port;
		private String username;
		private String password;
Email email;
		public synchronized void sendMail(String subject, String body,  String recipients, String sender, String propertyurl ) throws Exception
		{
			try{


		System.out.println("---------------Inside try of mail-------------KLK--"+ subject+","+body+","+recipients+","+sender+","+propertyurl);
			Properties prop = new Properties();
			prop.load(new FileInputStream(propertyurl));
			mailhost = prop.getProperty("mailhost");
			port = prop.getProperty("port");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			System.out.println(password);
			System.out.println(username);
			System.out.println(port);
			System.out.println(mailhost);
			System.out.println(recipients);
			System.out.println("-------------------------------------------");
			
			 HtmlEmail email = new HtmlEmail();
	    	 String name="mukesh.pandey";
	    	 email.setHostName(mailhost);
	    	 email.setSmtpPort(465);
	    	 email.setAuthenticator(new DefaultAuthenticator(username, password));
	    	 email.setSSLOnConnect(true);
	    	 email.setFrom(username);
	    	 email.setSubject(subject);
	    	 email.setMsg(body);
	    	 email.addTo(recipients);
	      	 email.addTo(sender);
	    	 
	    	//  String message="<html>The  SaralHiring logo - <img src=\"cid:"+cid+"\"><br></br><body>" +
	    	 
	    	 /* URL url = new URL("http://72.52.98.18:8080/SaralHiring/img/logo.png");
	    	  String cid = email.embed(url, " ");
	    	  String message="<html>The  SaralHiring logo - <img src=\"cid:"+cid+"\"><br></br><body>" +
	    	     System.out.println(password);
		System.out.println(username);
		System.out.println(port);
		System.out.println(mailhost);
		System.out.println(recipients);
	    	  "<br></br>"+"<b>name</b> " +name+  " </td></tr>"+
	    	  "<tr><td>"+ "<b>name</b> " +name+    " </td></tr>"+
	    	  "<tr><td>"+ "<b>name</b> " +name+   " </td></tr>"+
	    	  "<tr><td>"+ "name " +name+  " </td></tr>"+
	    	  "<tr><td>"+ "name " +name+   " </td></tr>"+
	    	  "<tr><td>"+"name " +name+   " </td></tr>"+
	    	 " </table></body>"+*/
	    	  		/*"<br></br>Name of user" +
	    	  		"<br></br>name " +name+
	    	  		"<br></br> name " +name+
	    	  		"<br></br> name " +name+
	    	  		"<br></br> name " +name+*/
	    	  /*		"</table></html>";*/
	    	  // set the html message
	    	

	    	  // set the alternative message
	    	  email.setTextMsg("Your email client does not support HTML messages");
	System.out.println("mail send");
	    	  // send the email
	    	  email.send();
					}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			
		
		}

		public  SendEmail(Email msg) {
			email=msg;
					
			
			// TODO Auto-generated method stub
			
		}
		
		
		  public void run(){
			  try {
				  String proppath=getClass().getClassLoader().getResource("Mail.properties").getFile();
System.out.println(proppath);
				sendMail(email.getSubject(), email.getMsgBody(),  email.getEmail(), "mlaitkor@gmail.com", proppath );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
 }