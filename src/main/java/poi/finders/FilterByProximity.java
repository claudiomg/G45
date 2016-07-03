package poi.finders;

import poi.modelo.puntoDeInteres.POI;
import poi.utilidades.Posicion;

public class FilterByProximity implements PoiFilter {

	private Posicion position;

	public FilterByProximity(Posicion unaPosicion) {
		this.setPosition(unaPosicion);
	}

	@Override
	public boolean matches(POI unPOI) {
		return unPOI.estaCercaDe(this.getPosition());
	}

	public Posicion getPosition() {
		return position;
	}

	public void setPosition(Posicion position) {
		this.position = position;
	}

}
