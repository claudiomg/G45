package poi.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Posicion;

public class RepositorioPOI implements Repositorio {

	private static RepositorioPOI instance = new RepositorioPOI();
	private List<POI> pois = new ArrayList<POI>();
	public List<SucursalBanco> listaDeBancos = new ArrayList<SucursalBanco>();
	
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
	
	public void agregarPOISucursalBancaria(SucursalBanco sucursal){
		this.pois.add(sucursal);
		this.listaDeBancos.add(sucursal);
	}

	public void removerPOI(POI poi){
		this.pois.remove(poi);
	}

	public void modificarPOI(POI poi, Posicion posicion, String etiqueta, String etiqueta2) {
		this.pois.stream().filter(a -> a == poi).findFirst().get().modificarAtributos(posicion, etiqueta, etiqueta2);
			
		
	}
	}

