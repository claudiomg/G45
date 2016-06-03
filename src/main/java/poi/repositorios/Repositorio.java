package poi.repositorios;

import java.util.ArrayList;
import java.util.List;
import poi.modelo.puntoDeInteres.POI;

public interface Repositorio {
	public List<POI> pois = new ArrayList <POI>(); 
	
	public List <POI> getPois();
	

}
