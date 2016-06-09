package poi.utilidades;

import java.util.List;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.StringJoin.UrlValue;
import com.google.maps.model.LatLng;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.GeocodingApiRequest;


public class Calculo {

	
	public static double distanciaEnKilometros(Posicion unaPosicion, Posicion otraPosicion){
		double lat = unaPosicion.longitud;
		double lon =unaPosicion.longitud ;
		double lat2 = otraPosicion.longitud;
		double lon2 =otraPosicion.longitud ;
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDV2WMczZfNd2j01NNBByj75mBRz7AFeac");
		
		
		LatLng inicio = new LatLng(lat, lon);
		LatLng fin = new LatLng(lat2, lon2);
		
				String[] origen  = reverseGeocode(context,inicio);
				String[] destino = reverseGeocode(context,fin);
		

		
					
		DistanceMatrixApiRequest request =	newRequest(context);
		request = getDistanceMatrix( context,origen ,destino );
		
}
	public static double distanciaLineal(Posicion unaPosicion, Posicion otraPosicion){
		//latutud == 
		double theta = unaPosicion.longitud - otraPosicion.longitud;
		double cateto1 = Math.sin((unaPosicion.latitud * Math.PI)/180.0) * Math.sin((unaPosicion.latitud * Math.PI)/180.0) 
				+ Math.cos((unaPosicion.latitud * Math.PI)/180.0) * Math.cos((unaPosicion.latitud * Math.PI)/180.0) * Math.cos((theta * Math.PI)/180.0);
		cateto1 = Math.acos(cateto1);
		cateto1 = cateto1 * 180.0/ Math.PI;
		cateto1 = cateto1 * 60 * 1.1515;
		cateto1 = cateto1 * 1.609344;
		//longitud == 
		double theta1 = unaPosicion.longitud - unaPosicion.longitud;
		double cateto2 = Math.sin((unaPosicion.latitud * Math.PI)/180.0) * Math.sin((otraPosicion.latitud * Math.PI)/180.0) 
				+ Math.cos((unaPosicion.latitud * Math.PI)/180.0) * Math.cos((otraPosicion.latitud * Math.PI)/180.0) * Math.cos((theta1 * Math.PI)/180.0);
		cateto2 = Math.acos(cateto2);
		cateto2 = cateto2 * 180.0/ Math.PI;
		cateto2 = cateto2 * 60 * 1.1515;
		cateto2 = cateto2 * 1.609344;
		return cateto1 + cateto2;
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
