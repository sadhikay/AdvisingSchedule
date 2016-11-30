/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class CustomEmail {

	final String emailPort = "587";// gmail's smtp port
	final String smtpAuth = "true";
	final String starttls = "true";
	final String emailHost = "smtp.gmail.com";

	String fromEmail;
	String fromPassword;
	String toEmailList;
	String emailSubject;
	String emailBody;

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public CustomEmail() {

	}

	public CustomEmail(String fromEmail, String fromPassword,
			String toEmailList, String emailSubject, String emailBody) {
		this.fromEmail = fromEmail;
		this.fromPassword = fromPassword;
		this.toEmailList = toEmailList;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", smtpAuth);
		emailProperties.put("mail.smtp.starttls.enable", starttls);
		
		mailSession = Session.getInstance(emailProperties,
		        new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(fromEmail, fromPassword);
		            }
		        });
		
	}

	public MimeMessage createEmailMessage() throws AddressException,
			MessagingException, UnsupportedEncodingException {

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
		/*for (String toEmail : toEmailList) {
			Log.i("GMail","toEmail: "+toEmail);
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
		}*/
		String toEmail=toEmailList;
		
		emailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(toEmail));
		
		emailMessage.setSubject(emailSubject);
		
		//emailMessage.setContent(emailBody, "text/html");// for a html email
		emailMessage.setText(emailBody);// for a text email
		
		
		return emailMessage;
	}

	public void sendEmail() throws AddressException, MessagingException {

		
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromEmail, fromPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		
	}

}
