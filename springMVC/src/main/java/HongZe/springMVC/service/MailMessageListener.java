package HongZe.springMVC.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import HongZe.springMVC.web.MailMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

@Component
public class MailMessageListener {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	MailService mailService;

	@JmsListener(destination = "jms/queue/mail", concurrency = "10")
	public void onMailMessageReceived(Message message)
			throws JMSException, JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		logger.info("receive message: " + message);
		if (message instanceof TextMessage) {
			String text = ((TextMessage) message).getText();
			MailMessage mm = objectMapper.readValue(text, MailMessage.class);
			mailService.sendRegistrationMail(mm);
		} else {
			logger.info("unable to send non-text message.");
		}
	}
}
