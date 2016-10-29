package poi.dataImport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioPOI;

public abstract class DataImportService {
	
	String keyApi = "AIzaSyC4l-KP4pCaBZ6DZSxEQkQD6kwGzhjM6ag";
    
    public void importData() {
    	String uri = this.getRadarSearchUri();
		String context = this.getRequestContext(uri);
	    try {
			JSONObject json = new JSONObject(context);
			if ( json.get("status").toString().equals("OK") ){
				JSONArray results = json.getJSONArray("results");
	            for (int i = 0; i < results.length(); i++) {
	                JSONObject poiJson = results.getJSONObject(i);  
	                this.processData(poiJson);
	            } 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    protected String keyGoogleApi() {
		return keyApi;
	}
    
    protected JSONObject getPlaceData(String placeID) {
		String uri = this.getPlaceDetailsUri(placeID);
		String context = this.getRequestContext(uri);
	    try {
			JSONObject json = new JSONObject(context);
			if ( json.get("status").toString().equals("OK") ){
				return json.getJSONObject("result");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}

	private String getRequestContext(String uri) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(uri);
			HttpResponse response;
			response = client.execute(request);
			BufferedReader buffer = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
			StringBuilder stringBuilder = new StringBuilder();
			String line="";
			while ((line = buffer.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;	
	}
    
    private String getPlaceDetailsUri(String placeID) {
    	return "https://maps.googleapis.com/maps/api/place/details/json?" +
    			"placeid=" + placeID + "&" +
    			"key=" + this.keyGoogleApi();
	}
    
    protected void persistPoi(POI banco) {
		// por ahora solo lo agrega al repo
		RepositorioPOI.getInstance().agregarRegistro(banco);
		System.out.println("create: "+ banco.toString());
	}

	abstract String getRadarSearchUri();

	abstract void processData(JSONObject poi) throws JSONException;
}
