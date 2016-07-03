package poi.finders;

import poi.modelo.puntoDeInteres.POI;

public interface PoiFilter {
	public boolean matches(POI unPOI);
}
