package poi.finders;

import java.util.Arrays;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;

public class FilterByTag implements PoiFilter {

	private String palabraBuscada;

	public FilterByTag(String palabraBuscada) {
		this.setPalabraBuscada(palabraBuscada);
	}

	@Override
	public boolean matches(POI unPOI) {
		//devuelvo true si alguna palabra de palabra buscada machea con el poi
		List<String> splited = Arrays.asList(palabraBuscada.split(" "));
		return splited.stream().anyMatch(string -> unPOI.matches(string));
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

}
