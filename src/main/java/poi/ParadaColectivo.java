package poi;

import calculo.Calculo;
import usuario.Posicion;

public class ParadaColectivo implements POI{
	private Posicion posicion;

	public ParadaColectivo() {
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < 0.1;
	};

}
