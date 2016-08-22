package poi.repositorios;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import poi.modelo.puntoDeInteres.DarDeBaja;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class RepositorioPOIsADarDeBaja extends RepositorioPOI {
	
	protected static RepositorioPOIsADarDeBaja instance;
	protected RepositorioPOIsADarDeBaja() { /*Existe para anular la instanciacion*/ };
	public static RepositorioPOIsADarDeBaja getInstance() {
		if(instance == null) {
			instance = new RepositorioPOIsADarDeBaja();
		}
		return instance;
	}
	
	public void actualizarRepositorio() throws Exception{
		
		String uri = "https://demo3537367.mockable.io/trash/pois";
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		
		InputStream json = connection.getInputStream();
		
		String poiAEliminarJson = convertStreamToString(json);
		
		JsonArray jsonArray = JsonArray.readFrom(poiAEliminarJson);
		for (JsonValue value : jsonArray) 
		{
			JsonObject jsonObject = (JsonObject) value;
			int id = jsonObject.get("id").asInt();
			String deleteAt = jsonObject.get("deletedAt").asString();
			DarDeBaja POIADarDeBaja = new DarDeBaja();
			POIADarDeBaja.setId(id);
			POIADarDeBaja.setDeleteAt(deleteAt);
			this.agregarRegistro(POIADarDeBaja);
			
		}
		
		connection.disconnect();
		
		
	}

	static String convertStreamToString(InputStream stream) {
		java.util.Scanner s = new Scanner(stream).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
