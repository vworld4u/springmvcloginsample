package com.vworld4u.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.vworld4u.models.User;

@Service("registrationHandler")
public class RegistrationHandlerImpl implements RegistrationHandler {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationHandler.class);
	
	@Autowired JavaMailSender mailsender;

	@Override
	public void sendRegistrationEmail(User user) {
		logger.info("sendRegistrationEmail : User = " + user);
		try {
			MimeMessage msg = createRegistrationMessage(user, mailsender);
			logger.info("sendRegistrationEmail : Sending Email = " + msg);
			mailsender.send(msg);
		} catch (Exception e) {
			logger.error("Error sending the Registration Email : ", e);
			e.printStackTrace();
		}
	}

	@Override
	public void verifyUser(String code, String email) {
		
	}

	public SimpleMailMessage getMessage(User user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setText("Hello World!!");
		msg.setTo("vworld4u@gmail.com");
		msg.setSubject("Test Message from Spring MVC");
		msg.setFrom("vworld14u@gmail.com");
		return msg;
	}
	
	public MimeMessage createRegistrationMessage(User user, JavaMailSender mailsender) throws MessagingException {
		MimeMessage msg = mailsender.createMimeMessage();
		msg.setFrom("vworld14u@gmail.com");
		msg.setRecipients(RecipientType.TO, "vworld4u@gmail.com");
		msg.setSubject("Test Message from Spring MVC Application");
		msg.setText("Hello World! \n\t This is a test application!");
		return msg;
	}
}
