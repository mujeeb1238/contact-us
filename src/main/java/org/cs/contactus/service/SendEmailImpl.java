package org.cs.contactus.service;

import org.cs.contactus.domain.form.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@EnableAsync
public class SendEmailImpl implements SendEmail {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SendEmailImpl.class);
	
	
	@Value("${email.from}")
    private String from;
	
	@Value("${email.to}")
    private String to;
	
	@Value("${email.password}")
    private String password;
	
	@Value("${email.subject}")
    private String subject;
	
	
	
	public SendEmailImpl() {}
	
	
	@Async("emailWorkerExecutor")
	@Override
	public void sendEmail(Contact contactInfo){
		/*java.net.UnknownHostException: smtp.gmail.com Some hit the UnknownHostException: smtp.gmail.com, try ping smtp.gmail.com and make sure you got a response (able to access). Often times, your connection may block by your firewall or proxy behind.*/
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		//Only for development
		props.put("mail.smtp.debug", "true");
		
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from,password);
				}
			});

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(subject);

	         StringBuilder sb = new StringBuilder();
	         sb.append("<h4>").append("Name: ").append(contactInfo.getFullName()).append("</h4>").append("</br>")
	         .append("<h4>").append("Contact Number: ").append(contactInfo.getContactNumber()).append("</h4>").append("</br>");
	         /*.append("<h4>").append("Business: ").append(contactInfo.getBusiness()).append("</h4>").append("</br>");*/
	         // Send the actual HTML message, as big as you like
	         message.setContent(sb.toString(), "text/html" );

	         // Send message
	         Transport.send(message);
	         LOGGER.info("Email sent!");
		} catch(AuthenticationFailedException ae){
			LOGGER.error("AuthenticationFailedException: "+ae.getMessage());
		}catch (MessagingException e) {
			LOGGER.error(e.getMessage());
		}
		
	}
		
}