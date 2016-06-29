package poi.utilidades;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static MailSender instance = null;
		
	private MailSender(){		
	};

	public static MailSender getInstance() {
		if(instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	public void enviarMail(String asunto, String detalleMail, String mail) {

		System.out.println(mail);
		
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port", "587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "grupo45dds@gmail.com");

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

		try {
			MimeMessage mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress("grupo45dds@gmail.com"));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			mensaje.setSubject(asunto);
			mensaje.setText(detalleMail);
			Transport t = session.getTransport("smtp");
			t.connect("grupo45dds@gmail.com",
					"45grupo45");
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			t.close();			
		} catch (Exception e) {
			e.getMessage();
		}
	}	
}
