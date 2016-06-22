package poi.repositorios;

import java.util.ArrayList;
import java.util.List;
import poi.modelo.puntoDeInteres.POI;

public abstract class RepositorioAbstracto {
	
	protected static RepositorioAbstracto instance = null;
	protected RepositorioAbstracto() { /*Existe para anular la instanciacion*/ };
	
	public List<POI> pois = new ArrayList <POI>(); 

	public void agregarPOI(POI poi){
		this.pois.add(poi);
	}
	public void eliminarPOI(POI poi){
		this.pois.remove(poi);
	}
}
