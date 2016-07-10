package poi.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poi.repositorios.RepositorioConsultas;

public abstract class ReporteAbstracto {
	
	List<HashMap<String, Object>> results = new ArrayList<>();
	RepositorioConsultas repository = RepositorioConsultas.getInstance();

	public abstract void dumpReport();
	//este metodo debe ser llamado desde el controller para armar la grilla de resultados, devuelve this.results
	
	public List<HashMap<String, Object>> getResults() {
		return this.results;
	}

	protected void addResult(HashMap<String, Object> result) {
		this.getResults().add(result);		
	}
	
	protected RepositorioConsultas getRepository() {
		return this.repository;
	}
}

