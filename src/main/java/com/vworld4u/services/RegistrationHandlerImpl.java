package com.vworld4u.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.vworld4u.models.PendingRegistration;
import com.vworld4u.models.User;
import com.vworld4u.repositories.PendingRegistrationRepository;
import com.vworld4u.repositories.UserRepository;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service("registrationHandler")
public class RegistrationHandlerImpl implements RegistrationHandler {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationHandler.class);
	
	@Autowired JavaMailSender mailsender;
	@Autowired PendingRegistrationRepository prrepo;
	@Autowired UserRepository userRepository;
	@Autowired FreeMarkerConfigurer configurer;

	@Override
	public void sendRegistrationEmail(User user) {
		logger.info("sendRegistrationEmail : " + user);
		PendingRegistration registration = prrepo.findByEmail(user.getEmail());
		if (registration == null) {
			logger.info("Saving the Pending Registration");
			registration = new PendingRegistration();
			registration.setUser(user);
			registration.setCreationTime(new Date());
			registration.setEmail(user.getEmail());
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		Date validUpto = cal.getTime();
		registration.setValidUpto(validUpto);
		registration.setVerificationCode(getRandomVerificationCode(user));
		registration = prrepo.save(registration);
		user.setActive(false);
		userRepository.save(user);
		logger.info("sendRegistrationEmail : User = " + user);
		try {
			String url = "http://localhost:8080/verifyregistration/" + Base64.getEncoder().encodeToString(user.getEmail().getBytes()) + "/" + registration.getVerificationCode();
 			logger.info("URL For Verification " + url);
			MimeMessage msg = createRegistrationMessage(url, user, mailsender);
			logger.info("sendRegistrationEmail : Sending Email = " + msg);
			mailsender.send(msg);
			logger.info("Message sent successfully!");
			registration.setEmailSentOn(new Date());
			registration = prrepo.save(registration);
			logger.info("Email Sent for Registration : " + registration);
		} catch (Exception e) {
			logger.error("Error sending the Registration Email : ", e);
			e.printStackTrace();
		}
	}

	public String getRandomVerificationCode(User user) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	@Override
	public void verifyUser(String code, String email) throws Exception {
		logger.info("verifyUser: " + code + " Email = " + email);
		PendingRegistration reg = prrepo.findByEmail(email);
		if (reg == null) {
			logger.error("No Pending Registration Found for Email : " + email);
			throw new Exception("No Pending Registration pending for email specified");
		}
		if (reg.getValidUpto().before(new Date())) {
			logger.error("Verification is happening after specified time (" + reg.getValidUpto() + ") ");
			sendRegistrationEmail(reg.getUser());
			throw new Exception("Verification is happening after specified time. We will send another verification code now. Please verify again.");
		}
		User user = reg.getUser();
		user.setActive(true);
		userRepository.save(user);
		reg.setVerifiedOn(new Date());
		reg = prrepo.save(reg);
		logger.info("Pending Registration completed : " + reg);
	}

	public SimpleMailMessage getMessage(User user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setText("Hello World!!");
		msg.setTo("vworld4u@gmail.com");
		msg.setSubject("Test Message from Spring MVC");
		msg.setFrom("vworld14u@gmail.com");
		return msg;
	}
	
	public MimeMessage createRegistrationMessage(String url, User user, JavaMailSender mailsender) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Template template = configurer.getConfiguration().getTemplate("verifyregistration.ftl");
		Map<String, String> model = new HashMap<>();
		model.put("appUrl", url);
		model.put("name", user.getName());
		String messagehtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		logger.info("Message HTML Loaded : " + messagehtml);
		MimeMessage msg = mailsender.createMimeMessage();
		msg.setFrom("vworld14u@gmail.com");
		msg.setRecipients(RecipientType.TO, "vworld4u@gmail.com");
		msg.setSubject("Test Message from Spring MVC Application");
		msg.setContent(messagehtml, "text/html");
		return msg;
	}
}
