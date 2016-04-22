package poi.utilidades;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;

public class RepositorioPOI {

	private static RepositorioPOI instance = new RepositorioPOI();
	private List<POI> pois = new ArrayList<POI>();
	
	private RepositorioPOI(){		
	}
	
	public static RepositorioPOI getInstance(){
		return instance;
	}

	public List<POI> getPois() {
		return pois;
	}

	public void setPois(List<POI> pois) {
		this.pois = pois;
	}	

	public void agregarPOI(POI poi){
		this.pois.add(poi);
	}

	public void removerPOI(POI poi){
		this.pois.remove(poi);
	}
}
