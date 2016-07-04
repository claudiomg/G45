package poi.utilidades;

import java.time.LocalDate;
import poi.modelo.usuario.Terminal;

public class Consulta {
	private double comienzoProceso;
	private double finProceso;
	private double tiempoProceso;
	private double tiempoProcesamientoMaximo = 10.0;
	private HistorialConsulta historial;
	private String palabraBuscada;
	private Terminal user;
	
	public Consulta(Terminal user, String palabraBuscada) {
		this.setUser(user);
		this.setPalabraBuscada(palabraBuscada);
	}


	public HistorialConsulta getHistorial() {
		return historial;
	}


	public void setHistorial(HistorialConsulta historial) {
		this.historial = historial;
	}


	public String getPalabraBuscada() {
		return palabraBuscada;
	}


	public Terminal getUser() {
		return user;
	}


	public void setComienzoProceso(double comienzoProceso) {
		this.comienzoProceso = comienzoProceso;
	}


	public void setFinProceso(double finProceso) {
		this.finProceso = finProceso;
	}


	public void setTiempoProceso(double tiempoProceso) {
		this.tiempoProceso = tiempoProceso;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

	public void calcularTiempoProceso() {
		this.setTiempoProceso(this.getComienzoProceso() - this.getFinProceso());
		//lo comento porque me parece que ahora ya no hace falta el historial
		//this.getHistorial().setTiempoProceso(this.getTiempoProceso());
		if (this.getTiempoProceso() > this.getTiempoProcesamientoMaximo()){
			Notificador.informarProcesamientoExcesivo(this.getTiempoProceso() - this.getTiempoProcesamientoMaximo());
		}
	}
	
	public double getTiempoProcesamientoMaximo() {
		return tiempoProcesamientoMaximo;
	}

	public void setTiempoProcesamientoMaximo(double tiempoProcesamientoMaximo) {
		this.tiempoProcesamientoMaximo = tiempoProcesamientoMaximo;
	}

	public double getComienzoProceso() {
		return comienzoProceso;
	}

	public double getFinProceso() {
		return finProceso;
	}

	public double getTiempoProceso() {
		return tiempoProceso;
	}
	public void setHistorial(LocalDate fecha, String nombre) {
		this.historial = new HistorialConsulta(fecha, nombre);
		this.historial.agregarARepositorio();
	}	

	public void generarHistorial(String frase) {
		this.historial.setFraseBuscada(frase);
	}

	public void setUser(Terminal user) {
		this.user = user;
	}
}
