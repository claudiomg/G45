package poi.utilidades;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioConsultas;

public class Consulta {
	private LocalDateTime comienzoProceso;
	private LocalDateTime finProceso;
	private Duration duracionProceso;
	private String palabraBuscada;
	private Terminal user;
	
	public Consulta(Terminal user, String palabraBuscada) {
		this.setUser(user);
		this.setPalabraBuscada(palabraBuscada);
	}
	
	public void calcularTiempoProceso() {
		Duration duration = Duration.between(this.getComienzoProceso(), this.getFinProceso());
		Duration maximoPermitido = PoiSystemConfiguration.getInstance().getTiempoProcesamientoMaximo();
		this.setDuracionProceso(duration);
		if (duration.toMillis() > maximoPermitido.toMillis() ){
			Notificador.informarProcesamientoExcesivo(this);
		}
	}
	
	public LocalDate getFecha(){
		//devuelvo la fecha de inicio
		return this.getComienzoProceso().toLocalDate();
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public Terminal getUser() {
		return user;
	}

	public void setComienzoProceso(LocalDateTime comienzoProceso) {
		this.comienzoProceso = comienzoProceso;
	}

	public void setFinProceso(LocalDateTime finProceso) {
		this.finProceso = finProceso;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

	public LocalDateTime getComienzoProceso() {
		return comienzoProceso;
	}
	public LocalDateTime getFinProceso() {
		return finProceso;
	}
	public void setUser(Terminal user) {
		this.user = user;
	}
	public Duration getDuracionProceso() {
		return duracionProceso;
	}
	public void setDuracionProceso(Duration duracionProceso) {
		this.duracionProceso = duracionProceso;
	}

	public void persist() {
		//me guardo en el repo de consultas
		RepositorioConsultas.getInstance().agregarRegistro(this);		
	}
}
