package poi.utilidades;

import java.time.LocalDate;


import poi.repositorios.RepositorioHistoriales;

public class HistorialConsulta  {
	private RepositorioHistoriales repositorio;
	private String nombreTerminal;
	private double tiempoProceso;
	private LocalDate fecha;
	private String fraseBuscada;
	
	public HistorialConsulta(LocalDate fecha, String nombreTerminal){
		this.fecha = fecha;
		this.nombreTerminal= nombreTerminal;
		this.repositorio= RepositorioHistoriales.getInstance();
		
	}
	
	public void setFraseBuscada(String frase){
		this.fraseBuscada = frase;
	}
	
	public void setTiempoProceso(double tiempo){
		this.tiempoProceso = tiempo;
	}
	
	

	public void agregarARepositorio() {
			
		this.repositorio.agregarHistorial(this);		
		
	}
	

}
