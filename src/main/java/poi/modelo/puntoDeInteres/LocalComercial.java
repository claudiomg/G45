package poi.modelo.puntoDeInteres;

import poi.utilidades.Calculo;
import poi.utilidades.Posicion;


public class LocalComercial extends POI{
    
	private RadioCercania radioDeCercania;
	
	public LocalComercial(RadioCercania	radioDeLocal){
		this.radioDeCercania = radioDeLocal;
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < radioDeCercania.getValue();
	}


}
