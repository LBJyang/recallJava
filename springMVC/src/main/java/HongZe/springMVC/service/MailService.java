package HongZe.springMVC.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import HongZe.springMVC.entity.User;
import HongZe.springMVC.web.MailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailService {
	@Value("${smtp.from}")
	String from;
	@Autowired
	JavaMailSender javaMailSender;

	final Logger logger = LoggerFactory.getLogger(getClass());

	public void sendRegistrationMail(User user) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
		try {
			messageHelper.setFrom(from);
			messageHelper.setTo(user.getEmail());
			messageHelper.setSubject("Welcome!");
			String htmlString = String.format("<p>Hi, %s,</p><p>Welcome!</p><p>Sent at %s</p>", user.getName(),
					LocalDateTime.now());
			messageHelper.setText(htmlString, true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendRegistrationMail(MailMessage mm) {
		// TODO Auto-generated method stub
		logger.info("[send mail] sending registration mail to {}...", mm.email);
		// TODO: simulate a long-time task:
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		logger.info("[send mail] registration mail was sent to {}.", mm.email);
	}
}
