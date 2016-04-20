package poi;

import calculo.Calculo;
import usuario.Posicion;

public class LibreriaEscolar extends LocalComercial{
	private final double RADIO_CERCANIA = 0.5;

	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < RADIO_CERCANIA;
	};
}
