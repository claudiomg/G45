package poi.utilidades;

import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.usuario.RepositorioAdministrador;

public class Notificador {
	public static Notificador instance = null;
	public static String mensajeTiempoExcesivo = 
			"Sr Administrador: Un proceso se ha extendido más de lo permitido. Tiempo excedido: "; 
	public static String asuntoTiempoExcesivo = "Procesamiento excedido";
		
	private Notificador(){		
	};

	public static Notificador getInstance() {
		if(instance == null) {
			instance = new Notificador();
		}
		return instance;
	}

	public static void informarProcesamientoExcesivo(double tiempoExcedido) {
		String detalleMail = mensajeTiempoExcesivo + String.format ("%.2f", tiempoExcedido) + " segundos.";
		List<String> listaMails = RepositorioAdministrador.getInstance().getAdministradores().stream()
				.map(administrador->administrador.getMail()).collect(Collectors.toList());;
		MailSender.getInstance().enviarMail(asuntoTiempoExcesivo, detalleMail, listaMails);
	}
	
	
}
