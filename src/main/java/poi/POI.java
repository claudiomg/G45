package poi;

import usuario.Posicion;

public interface POI {
	public boolean estaCercaDe(Posicion posicion);
	public void setPosicion(Posicion posicion);
}
