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

	public static boolean coordenadasEnComuna(List<Posicion> zona, Posicion posicion) {
		int i, j;
	    boolean dentro = false;
	    //create an array of coordinates from the region boundary list
//	    Posicion[] verts = zona.getBoundary().toArray(new Posicion[zona.size()]);
	    Posicion[] vertices = new Posicion[zona.size()];
	    vertices = zona.toArray(vertices);
	    int sides = vertices.length;
	    for (i = 0, j = sides - 1; i < sides; j = i++) {
	    	//verifying if your coordinate is inside your region
	        if ((((vertices[i].getLongitud() <= posicion.getLongitud()) && (posicion.getLongitud() < vertices[j].getLongitud())
	                 ) || ((vertices[j].getLongitud() <= posicion.getLongitud()) && (posicion.getLongitud() < vertices[i].getLongitud()))) 
	        		&& (posicion.getLatitud() < (vertices[j].getLatitud() - vertices[i].getLatitud()) * 
	        				(posicion.getLongitud() - vertices[i].getLongitud()) / (vertices[j].getLongitud() - vertices[i].getLongitud()) + vertices[i].getLatitud())) {
	                dentro = !dentro;
	            }
	        }
	        return dentro;
	    }
}
