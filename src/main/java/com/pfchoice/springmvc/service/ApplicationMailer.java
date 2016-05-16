package com.pfchoice.springmvc.service;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ApplicationMailer 
{
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
    private String emailId;
    
    private Properties emailProperties;

    private Session session;

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}


	/**
	 * @param emailProperties the emailProperties to set
	 */
	public void setEmailProperties(Properties emailProperties) {
		this.emailProperties = emailProperties;
	}

	/**
	 * @return the emailProperties
	 */
	public Properties getEmailProperties() {
		return emailProperties;
	}

    /**
     * This method will send compose and send the message 
     * */
	public void sendMail(String to, String subject, String body) 
    {
    	 
		final Message message = new MimeMessage(session);
		try {
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(emailId));
			message.setSubject(subject);
			message.setText(body);
			message.setSentDate(new Date());
			Transport.send(message);
		} catch (AddressException e) {
			LOGGER.log(Level.INFO, "AddressException: " + e.getMessage(), e);
		} catch (MessagingException e) {
			LOGGER.log(Level.INFO, "MessagingException: " + e.getMessage(), e);
		}
    }
 
}