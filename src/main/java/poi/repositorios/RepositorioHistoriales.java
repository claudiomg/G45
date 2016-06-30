package poi.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.utilidades.HistorialConsulta;

public class RepositorioHistoriales {
	
	protected static RepositorioHistoriales instance = null;
	public List<HistorialConsulta> historialesConsultas = new ArrayList <HistorialConsulta>();
	
	public static RepositorioHistoriales getInstance(){
		if (instance == null){
			instance = new RepositorioHistoriales();
		}
		return instance;
	}
	
	
	public void agregarHistorial(HistorialConsulta historial){
		historialesConsultas.add(historial);
	}
	
	public List<HistorialConsulta> getHistoriales (){
		return this.historialesConsultas;
	}


	public long cantidaDeConsultasHechasEnUnaTerminalEnUnaFecha(String unaTerminal, LocalDate unaFecha) {
		List<HistorialConsulta> unaLista = new ArrayList<HistorialConsulta>();
		unaLista = this.filtraConsultaPorTerminal(historialesConsultas,unaTerminal);
		return filtrarConsultaPorFecha(unaLista,unaFecha).size();
		
	}


	public List<HistorialConsulta> filtrarConsultaPorFecha(List<HistorialConsulta> consultas2, LocalDate unaFecha) {
        
		return consultas2
		.stream()
		.filter(unHistorial -> (unHistorial.getFecha() == unaFecha))
		.collect(Collectors.toList());
	}


	public List<HistorialConsulta> filtraConsultaPorTerminal(List<HistorialConsulta> consultas,String unaTerminal) {
		
		return consultas
			.stream()
			.filter(unHistorial -> (unHistorial.getNombreTerminal() == unaTerminal))
			.collect(Collectors.toList());
	
	}
	
	

	

}
