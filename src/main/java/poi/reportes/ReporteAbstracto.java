package poi.reportes;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import poi.repositorios.RepositorioHistoriales;

public abstract class ReporteAbstracto {
	
	JsonArray results = new JsonArray();
	RepositorioHistoriales repositorioDeHistorial;

	public abstract JsonArray dumpReport();
	//este metodo debe ser llamado desde el controller para armar la grilla de resultados, devuelve this.results
	
	private JsonArray getResults() {
		return this.results;
	}

	protected void addResult(JsonObject result) {
		this.getResults().add(result);		
	}
	public JsonObject createResult( HashMap<String, String> columns) {
		JsonObject result = new JsonObject();
		for (String property : columns.keySet()){
			result.addProperty(property, columns.get(property));
		}
		this.addResult(result);//agrego el json object a la lista de resultados
		return result;
	}
	
	public RepositorioHistoriales getRepositorioDeHistorial() {
		return repositorioDeHistorial;
	}

	public void setRepositorioDeHistorial(RepositorioHistoriales repositorioDeHistorial) {
		this.repositorioDeHistorial = repositorioDeHistorial;
	}

}

