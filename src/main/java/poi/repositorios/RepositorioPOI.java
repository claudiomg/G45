package poi.repositorios;

import poi.modelo.puntoDeInteres.POI;
import poi.utilidades.Posicion;

public class RepositorioPOI extends RepositorioAbstracto {

	//All GameActions are singletons
	public static RepositorioAbstracto getInstance() {
		if(instance == null) {
			instance = new RepositorioPOI();
		}
		return instance;
	}
	protected RepositorioPOI() { /*Existe para anular la instanciacion*/ };

	
}

