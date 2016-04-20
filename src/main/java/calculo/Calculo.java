package calculo;

import java.util.List;

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
	
	public static boolean coordenadasEnComuna(List<Posicion> vertices, Posicion tap) {
        int intersectCount = 0;
        for(int j=0; j<vertices.size()-1; j++) {
            if( LineIntersect(tap, vertices.get(j), vertices.get(j+1)) ) {
                intersectCount++;
            }
        }
        return (intersectCount%2) == 1; // odd = inside, even = outside;
    }
    
	public static boolean LineIntersect(Posicion tap, Posicion vertA, Posicion vertB) {
        double aY = vertA.getLatitud();
        double bY = vertB.getLatitud();
        double aX = vertA.getLongitud();
        double bX = vertB.getLongitud();
        double pY = tap.getLatitud();
        double pX = tap.getLongitud();
        if ( (aY>pY && bY>pY) || (aY<pY && bY<pY) || (aX<pX && bX<pX) ) {
            return false; }
        double m = (aY-bY) / (aX-bX);               
        double bee = (-aX) * m + aY;                // y = mx + b
        double x = (pY - bee) / m;                 
        return x > pX;
    }	
}
