package usuario;

import poi.POI;

public class Consulta {

	public boolean sonCercanos(Posicion posicion, POI poi) {		
		return poi.estaCercaDe(posicion);
	}
}
