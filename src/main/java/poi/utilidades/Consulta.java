package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioPOI;

public class Consulta {
	
	private List<RepositorioPOI> repositorios;
	private List<POI> poisEncontrados = new ArrayList<POI>();	

	
	public Consulta(RepositorioPOI repositorio) {
		this.agregarRepositorio(repositorio);
	}

	private void agregarRepositorio(RepositorioPOI repositorio) {
		this.repositorios.add(repositorio);
	}

	public List<POI> getPoisEncontrados() {
		return poisEncontrados;
	}

	public boolean sonCercanos(Posicion posicion, POI poi) {		
		return poi.estaCercaDe(posicion);
	}

	public List<POI> buscarPorPalabra(String palabra) {
		for ( RepositorioPOI unRepo : this.repositorios ) {
			this.agregarResultados(this.buscarEnRepositorio(palabra, unRepo));
		}
		return this.poisEncontrados;
	}
	
	private List<POI> buscarEnRepositorio(String palabra, RepositorioPOI unRepo) {
		Stream<POI> resultados;
		resultados = unRepo.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
		return resultados.collect(Collectors.toList());
	}

	private void agregarResultados(List<POI> resultados) {
		this.poisEncontrados.addAll(resultados);
	}
	
}
