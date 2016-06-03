package poi.utilidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioPOI;
import poi.repositorios.RepositorioCGPExternos;

public class Consulta {
	
	private RepositorioPOI repositorio;
	private List<POI> poisEncontrados = new ArrayList<POI>();
	private RepositorioCGPExternos repositorioCGPExterno;

	
	public Consulta(RepositorioPOI repositorio) {
		this.repositorio = repositorio;
	}
	
	public void setRepositorioCGPExterno(RepositorioCGPExternos repositorioCGPExterno){
		this.repositorioCGPExterno= repositorioCGPExterno;
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
	
	public Stream<POI> filtrarPorPalabra2 (String palabra){
		return repositorioCGPExterno.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
		
	}
	
	public List<POI> asignarAPoisEncontrados(Stream<POI> lista){
		return this.poisEncontrados = lista.collect(Collectors.toCollection(ArrayList::new));
	}
	
}
