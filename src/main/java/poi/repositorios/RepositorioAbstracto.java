package poi.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Posicion;

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
	
	public void limpiarPOIs() {
		this.pois = new ArrayList <POI>();
	}
	public List<POI> getPois() {
		return this.pois;
	}
	public List<SucursalBanco> getBancos() {
		return this.getPois()
			.stream()
			.filter(unPOI -> unPOI instanceof SucursalBanco)
			.map(unPOI -> (SucursalBanco) unPOI)
			.collect(Collectors.toList());
	}
	public void modificarPOI(POI poi, Posicion posicion, String etiqueta, String etiqueta2) {
		this.pois.stream().filter(a -> a == poi).findFirst().get().modificarAtributos(posicion, etiqueta, etiqueta2);
			
	}
}
