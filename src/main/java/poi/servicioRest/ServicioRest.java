package poi.servicioRest;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;

public class ServicioRest {
	protected static ServicioRest instance;
	protected ServicioRest() { /*Existe para anular la instanciacion*/ };
	
	public static ServicioRest getInstance() {
		if(instance == null) {
			instance = new ServicioRest();
		}
		return instance;
	}

	public void restBusqueda(List<HashMap<String, Object>> listado){				
		get("/pois", (req, res) -> listado, new JsonTransformer());
	}
	
	public void restHistorial(List<HashMap<String, Object>> listado){		
		get("/consultas", (req, res) -> {return listado;}, new JsonTransformer());
		this.instance = null;
	}	
	
}
