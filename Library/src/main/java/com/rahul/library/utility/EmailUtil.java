package com.rahul.library.utility;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Value("email.password")
	String senderPassword;

	public void sendmail() throws AddressException, MessagingException, IOException {
		
		
		   
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("rahrajput29@gmail.com",senderPassword );
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("rahrajput29@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rahrajput29@gmail.com"));
		   msg.setSubject("Books Upload Result");
		   msg.setContent("Books have been added/uploaded", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Books have been uploaded ", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("C:\\Users\\rahul\\git\\Library\\Library\\src\\main\\resources\\images\\smile.png");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
}
