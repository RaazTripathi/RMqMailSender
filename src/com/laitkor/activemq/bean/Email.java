package com.laitkor.activemq.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class Email
{
	String subject;
	String email;
	String task;
	String date ;
	String msgBody;
	
	public String getSubject() {
		return subject;
	}
	public String getEmail() {
		return email;
	}
	public String getTask() {
		return task;
	}
	public String getDate() {
		return date;
	}
	public String getMsgBody() {
		return msgBody;
	}
	  @XmlElement
	public void setSubject(String subject) {
		this.subject = subject;
	}
	  @XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	  @XmlElement
	public void setTask(String task) {
		this.task = task;
	}
	  @XmlElement
	public void setDate(String date) {
		this.date = date;
	}
	  @XmlElement
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	
}
