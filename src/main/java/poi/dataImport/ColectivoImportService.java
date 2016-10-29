package poi.dataImport;

import java.util.Random;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class ColectivoImportService extends DataImportService {

	@Override
	String getRadarSearchUri() {
		return "https://maps.googleapis.com/maps/api/place/radarsearch/json?" +
				"location=-34.634757,-58.498956&" +
				"radius=1000&" +
				"types=bus_station&" +
				"key=" + this.keyGoogleApi();
	}

	@Override
	void processData(JSONObject responseJson) throws JSONException {
		try {
			JSONObject locationJson = responseJson.getJSONObject("geometry").getJSONObject("location");
			JSONObject poiJson = this.getPlaceData(responseJson.get("place_id").toString());
			JSONArray address = poiJson.getJSONArray("address_components");
			
			Posicion posicion = new Posicion(locationJson);
			Direccion direccion = new Direccion(address);
	    	ParadaColectivo colectivo = new ParadaColectivo("", posicion, direccion);
	    	this.setAditionalAttributes(colectivo);
	    	this.persistPoi(colectivo);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setAditionalAttributes(ParadaColectivo colectivo) {
		Random rn = new Random();
		colectivo.setLinea(new Integer(rn.nextInt(100)).toString());
	}

}
