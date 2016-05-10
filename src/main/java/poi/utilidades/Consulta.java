package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poi.modelo.puntoDeInteres.POI;

public class Consulta {
	
	private RepositorioPOI repositorio;
	private List<POI> poisEncontrados = new ArrayList<POI>();	

	
	public Consulta(RepositorioPOI repositorio) {
		this.repositorio = repositorio;
	}

	public List<POI> getPoisEncontrados() {
		return poisEncontrados;
	}

	public boolean sonCercanos(Posicion posicion, POI poi) {		
		return poi.estaCercaDe(posicion);
	}

	public List<POI> buscarPorPalabra(String palabra) {
		Stream<POI> listaFiltrada = filtrarPorParlabra(palabra);
		return asignarAPoisEncontrados(listaFiltrada);
	}
	
	public Stream<POI> filtrarPorParlabra(String palabra){
		return repositorio.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
		}
	
	public List<POI> asignarAPoisEncontrados(Stream<POI> lista){
		return this.poisEncontrados = lista.collect(Collectors.toCollection(ArrayList::new));
	}
	
}
