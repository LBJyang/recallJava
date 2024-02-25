/**
 * 
 */
package LEARN.SMPT;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

/**
 * 
 */
public class SendMail {

	/**
	 * @param args
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws IOException
	 */
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		// TODO Auto-generated method stub
		final String smtp = "smtp.139.com";
		final String userName = "15904082220@139.com";
		final String passWord = "Yf198610";
		String body = "<h>This mail is from Java!</h>";
		InputStream input = null;

		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, passWord);
			}
		});

		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("15904082220@139.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("yangfan6262008@gmail.com"));
		message.setSubject("This is from java", "UTF-8");
		Multipart multipart = new MimeMultipart();
		BodyPart textpart = new MimeBodyPart();
		textpart.setContent(body, "text/html;charset =utf-8");
		multipart.addBodyPart(textpart);
		BodyPart imagepart = new MimeBodyPart();
		imagepart.setFileName("imgae.png");
		imagepart.setDataHandler(new DataHandler(new ByteArrayDataSource(input, "application/octet-stream")));
		multipart.addBodyPart(imagepart);
		message.setContent(multipart);

		Transport.send(message);
	}

}
