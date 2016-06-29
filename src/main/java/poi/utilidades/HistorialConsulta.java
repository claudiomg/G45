package poi.utilidades;

import java.time.LocalDate;

public class HistorialConsulta {
	private String nombreTerminal;
	private long tiempoProceso;
	private LocalDate fecha;
	private String fraseBuscada;
	
	public HistorialConsulta(LocalDate fecha, String nombreTerminal){
		this.fecha = fecha;
		
	}
	
	public void setFraseBuscada(String frase){
		this.fraseBuscada = frase;
	}
	
	public void setTiempoProceso(long tiempo){
		this.tiempoProceso = tiempo;
	}
	

}
