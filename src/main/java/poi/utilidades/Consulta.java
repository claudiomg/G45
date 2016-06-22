package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioCGPExternos;
import poi.repositorios.RepositorioPOI;


public class Consulta {
	private RepositorioAbstracto repositorio2;
	private RepositorioAbstracto repositorio;
	private List<POI> poisEncontrados = new ArrayList<POI>();
	private List<POI> poisEncontradosEnExterno= new ArrayList<POI>();

	public Consulta(RepositorioAbstracto repositorio3) {
		this.repositorio = repositorio3;
	}
	
	public void setRepoExterno(RepositorioAbstracto repo2){
		this.repositorio2=repo2;
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
	
	public List<POI> buscarPorPalabraEnExterno(String palabra){
		Stream<POI> listaFiltrada2 = filtrarPorParlabraExternos(palabra);
	
	
	    return asignarAPoisEncontrados2(listaFiltrada2);
		
	}
	
	public Stream<POI> filtrarPorParlabra(String palabra){
		return repositorio.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
	}
	
	public Stream<POI> filtrarPorParlabraExternos(String palabra){
		return repositorio2.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
	}
	
	public List<POI> asignarAPoisEncontrados2(Stream<POI> lista){
		return this.setPoisEncontradosEnExterno(lista.collect(Collectors.toCollection(ArrayList::new)));
	} 
	
	public List<POI> asignarAPoisEncontrados(Stream<POI> lista){
		this.setPoisEncontradosEnExterno(lista.collect(Collectors.toCollection(ArrayList::new)));
		return this.getPoisEncontradosEnExterno();
	}

	public List<POI> getPoisEncontradosEnExterno() {
		return poisEncontradosEnExterno;
	}

	public List<POI> setPoisEncontradosEnExterno(List<POI> poisEncontradosEnExterno) {
		this.poisEncontradosEnExterno = poisEncontradosEnExterno;
		return poisEncontradosEnExterno;
	}
	
}
