package poi.utilidades;

import java.time.Duration;

import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;

public class Notificador {
	
	public static Notificador instance;
	private Notificador(){		
	};
	public static Notificador getInstance() {
		if(instance == null) {
			instance = new Notificador();
		}
		return instance;
	}

	public static void informarProcesamientoExcesivo(Consulta consulta) {
		Duration maximoPermitido = PoiSystemConfiguration.getInstance().getTiempoProcesamientoMaximo();
		Duration duracion = consulta.getDuracionProceso();
		String mensaje = "Sr Administrador: Una consulta ha extendido el tiempo de procesamiento definido." +
				"Terminal:" + String.format("\n%s", consulta.getUser().getUsuario()) +
				"Tiempo de proceso:" + String.format ("%.2f", duracion.toMillis()) + " milisegundos." +
				"Tiempo permitido:" + String.format ("%.2f", maximoPermitido.toMillis()) + " milisegundos."; 
		String asunto = "Procesamiento excedido";
		for (UsuarioPOI admin : RepositorioUsuarios.getInstance().getAdministradores()){
			MailSender.getInstance().enviarMail(((Administrador) admin).getMail(), asunto, mensaje);
		}
	}
	
}
