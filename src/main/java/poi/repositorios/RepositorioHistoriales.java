package poi.repositorios;

import java.util.ArrayList;
import java.util.List;

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

}
