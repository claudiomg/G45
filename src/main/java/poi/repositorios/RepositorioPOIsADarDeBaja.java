package poi.repositorios;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import poi.modelo.puntoDeInteres.DarDeBaja;


public class RepositorioPOIsADarDeBaja extends RepositorioAbstractoPOI{
	
	public List<DarDeBaja> registros = new ArrayList <DarDeBaja>();
	
	
	public List<DarDeBaja> obtenerRegistros() {
		return registros;
	}
    public void agregarRegistro(DarDeBaja registros) {
		this.registros.add(registros);
	}

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
	
	
	public List<Integer> sacarLosIdsDeLosRegistros(List<DarDeBaja> lista){
    	List<Integer> ids = new ArrayList<Integer>();
    	ids = lista.stream().map(elem -> elem.getId()).collect(Collectors.toList());
    	return ids;
     }

}
