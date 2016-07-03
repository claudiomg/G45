package poi.finders;

import poi.modelo.puntoDeInteres.POI;

public class FilterByTag implements PoiFilter {

	private String palabraBuscada;

	public FilterByTag(String palabraBuscada) {
		this.setPalabraBuscada(palabraBuscada);
	}

	@Override
	public boolean matches(POI unPOI) {
		//redefinir este metodo para que devuelva el valor verdadero
		return true;
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

}
