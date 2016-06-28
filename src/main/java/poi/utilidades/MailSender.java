package poi.utilidades;

import java.util.List;

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

	public void enviarMail(String asuntoTiempoExcesivo, String detalleMail, List<String> listaMails) {
		
	}

	
	
}
