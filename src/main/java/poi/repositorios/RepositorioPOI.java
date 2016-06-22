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

	public void modificarPOI(POI poi, Posicion posicion, String etiqueta, String etiqueta2) {
		this.pois.stream().filter(a -> a == poi).findFirst().get().modificarAtributos(posicion, etiqueta, etiqueta2);
			
	}
}

