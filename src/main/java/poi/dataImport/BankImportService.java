package poi.dataImport;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class BankImportService extends DataImportService {
	List<String> servicios = Arrays.asList(
			"Depósitos",
			"Transacciones",
			"Préstamos",
			"Asesoramiento financiero",
			"Cambio de moneda extranjera",
			"Cajas de seguridad"
		);
	
	@Override
	String getRadarSearchUri() {
		return "https://maps.googleapis.com/maps/api/place/radarsearch/json?" +
			"location=-34.607698,-58.450603&" +
			"radius=1000&" +
			"types=bank&" +
			"key=" + this.keyGoogleApi();
	}

	@Override
	void processData(JSONObject responseJson)  {
		try {
			JSONObject locationJson = responseJson.getJSONObject("geometry").getJSONObject("location");
			JSONObject poiJson = this.getPlaceData(responseJson.get("place_id").toString());
			JSONArray address = poiJson.getJSONArray("address_components");
			String name = poiJson.getString("name");
			
			Posicion posicion = new Posicion(locationJson);
			Direccion direccion = new Direccion(address);
	    	SucursalBanco banco = new SucursalBanco(name, posicion, direccion);
	    	this.setAditionalAttributes(banco);
	    	this.persistPoi(banco);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setAditionalAttributes(SucursalBanco banco) {
		Random rnd= new Random();
		for (String serv: servicios){
			if (rnd.nextInt(10) % 2 == 0){
				banco.agregarServicio(serv);
			}
		}
	}
}
