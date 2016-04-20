package poi;

import calculo.Calculo;
import usuario.Posicion;

public class Kiosco extends LocalComercial{	
	private final double RADIO_CERCANIA = 0.2;

	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < RADIO_CERCANIA;
	};
}
