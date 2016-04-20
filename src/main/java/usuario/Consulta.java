package usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poi.POI;

public class Consulta {

	public boolean sonCercanos(Posicion posicion, POI poi) {		
		return poi.estaCercaDe(posicion);
	}

	public List<POI> buscarPorPalabra(String palabra, List<POI> ListaPOIs) {
		Stream<POI> listaFiltrada = ListaPOIs.stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra) );
		return listaFiltrada.collect(Collectors.toCollection(ArrayList::new));
	}
}
