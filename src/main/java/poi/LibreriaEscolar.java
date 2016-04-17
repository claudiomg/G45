package poi;

import calculo.Calculo;
import usuario.Posicion;

public class LibreriaEscolar implements LocalComercial{
	private Posicion posicion;
	private final double RADIO_CERCANIA = 0.5;
	
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < RADIO_CERCANIA;
	};
}
