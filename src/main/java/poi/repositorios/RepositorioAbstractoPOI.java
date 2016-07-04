package poi.repositorios;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;

public abstract class RepositorioAbstractoPOI {
	
	public List<POI> registros = new ArrayList <POI>(); 

	public void agregarRegistro(POI unRegistro){
		this.registros.add(unRegistro);
	}
	public void eliminarRegistro(POI unRegistro){
		this.registros.remove(unRegistro);
	}
	public void cleanRepository() {
		this.registros = new ArrayList <POI>();
	}
	public List<POI> getRegistros() {
		return this.registros;
	}
}
