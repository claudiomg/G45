package poi;

import calculo.Calculo;
import usuario.Posicion;

public abstract class LocalComercial extends POI{
protected double RADIO_CERCANIA;
	
	public LocalComercial(){
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < RADIO_CERCANIA;
	}
	
}
