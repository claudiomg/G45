package poi;

import usuario.Posicion;

public class CGP implements POI{
	private Posicion posicion;

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}	
	public boolean estaCercaDe(Posicion posicion){
		return true;
	};
}
