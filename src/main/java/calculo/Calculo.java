package calculo;

import usuario.Posicion;

public class Calculo {

	public static double distanciaEnKilometros(Posicion unaPosicion, Posicion otraPosicion){
		double theta = unaPosicion.longitud - otraPosicion.longitud;
		double distancia = Math.sin((unaPosicion.latitud * Math.PI)/180.0) * Math.sin((otraPosicion.latitud * Math.PI)/180.0) 
				+ Math.cos((unaPosicion.latitud * Math.PI)/180.0) * Math.cos((otraPosicion.latitud * Math.PI)/180.0) * Math.cos((theta * Math.PI)/180.0);
		distancia = Math.acos(distancia);
		distancia = distancia * 180.0/ Math.PI;
		distancia = distancia * 60 * 1.1515;
		distancia = distancia * 1.609344;
		return (distancia);
	}
	

}
