package com.revature.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;


@Component
public class EmailSender {
	
	private String username;
	private String password;
	
	public EmailSender() {
		super();
		this.username = System.getenv("email_username");
		this.password = System.getenv("email_password");
	}

	private Session setSession() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        return Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
	}
	
	private Message setBasicMessage(String recipientAddress, String messageSubject) throws MessagingException {
		Message message = new MimeMessage(setSession());
		 message.setFrom(new InternetAddress(username));
         message.setRecipients(
                 Message.RecipientType.TO,
                 InternetAddress.parse(recipientAddress)
         );
         message.setSubject(messageSubject);
         return message;
	}
	
	public String HTMLWraper(String HTMLBody) {
		String header = "<div style='color:orange;  background-color: rgb(180, 26, 26); font-size: 70px; text-align: center;'>Pizza Palace</div>\n";
		String footer = "\n<footer style='text-align: center; font-size: 30px; background-color: lightblue;'>Company © Pizza Palace. All rights reserved.</footer>";
		
		
		return header + "<div style='background-color: lightblue; text-align: center;'" + HTMLBody + "</div>\n" + footer;
	}
	
	public void sendEmail(String recipientAddress, String messageSubject, String messageText) {

        try {

        	Message message = this.setBasicMessage(recipientAddress, messageSubject);
            message.setText(messageText);

            Transport.send(message);
            

        } catch (MessagingException e) {
           e.printStackTrace();
        }
	}
	
	public void sendHTMLEmail(String recipientAddress, String messageSubject, String htmlBody) {
		try {
			Message message = this.setBasicMessage(recipientAddress, messageSubject);
			message.setContent(htmlBody,
		             "text/html; charset=utf-8");
			
			Transport.send(message);

			
		} catch (MessagingException e) {
			e.printStackTrace();
        }
	}

}
