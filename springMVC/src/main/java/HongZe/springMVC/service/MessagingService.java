package HongZe.springMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import HongZe.springMVC.web.MailMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

@Component
public class MessagingService {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMailMessage(MailMessage msg) throws JsonProcessingException {
		String text = objectMapper.writeValueAsString(msg);
		jmsTemplate.send("jms/queue/mail", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(text);
			}
		});
	}
}
