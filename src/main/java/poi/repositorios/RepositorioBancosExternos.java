package poi.repositorios;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class RepositorioBancosExternos extends RepositorioAbstractoPOI {

	protected static RepositorioBancosExternos instance;
	protected RepositorioBancosExternos() { /*Existe para anular la instanciacion*/ };
	public static RepositorioBancosExternos getInstance() {
		if(instance == null) {
			instance = new RepositorioBancosExternos();
		}
		return instance;
	}
	
	public void actualizarRepositorio() throws Exception {
		this.cleanRepository();
		String uri =
			    "http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio";
			URL url = new URL(uri);
			HttpURLConnection connection =
			    (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			InputStream json = connection.getInputStream();
			
			String bancoJson = convertStreamToString(json);
			
			JsonArray jsonArray = JsonArray.readFrom(bancoJson);
			for (JsonValue value : jsonArray) 
			{
				
				JsonObject jsonObject = (JsonObject) value;
				String sucursal = jsonObject.get("sucursal").asString();
				String gerente = jsonObject.get("gerente").asString();
				
				String nombre  = jsonObject.get("banco").asString();
				double latitud = jsonObject.get("x").asDouble();
				double longitud = jsonObject.get("y").asDouble();
				Direccion direccion = new Direccion();
				Posicion posicion = new Posicion(latitud, longitud);
				SucursalBanco banco = new SucursalBanco(nombre, posicion, direccion);
				banco.setGerente(gerente);
				banco.setSucursal(sucursal);
				for (JsonValue servicio : jsonObject.get("servicios").asArray()){
					banco.agregarServicio(servicio.toString());
				}				
				this.agregarRegistro(banco);
			}
			
			connection.disconnect();
		}

	static String convertStreamToString(InputStream stream) {
	    java.util.Scanner s = new Scanner(stream).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
