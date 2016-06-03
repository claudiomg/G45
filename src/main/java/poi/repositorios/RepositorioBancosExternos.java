package poi.repositorios;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Posicion;

public class RepositorioBancosExternos implements Repositorio {

	private static RepositorioBancosExternos instance = new RepositorioBancosExternos();
	private List<SucursalBanco> bancos = new ArrayList<SucursalBanco>();
	private List<POI> Pois = new ArrayList<POI>();
	
	public static RepositorioBancosExternos getInstance(){
		return instance;
	}
	
	public List<SucursalBanco> getBancos() {
		return bancos;
	}
	public List<POI> getPois(){
		return Pois;
	}
	public void actualizarRepositorio() throws Exception {
		
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
				String nombre  = jsonObject.get("banco").asString();
				double latitud = jsonObject.get("x").asDouble();
				double longitud = jsonObject.get("y").asDouble();
				String sucursal = jsonObject.get("sucursal").asString();
				String gerente = jsonObject.get("gerente").asString();
				JsonArray servicios = jsonObject.get("servicios").asArray();
				
				Posicion posicion = new Posicion();
				posicion.setLatitud(latitud);
				posicion.setLongitud(longitud);
				
				List<String> etiquetas = new ArrayList<String>();
				etiquetas.add(nombre);
				etiquetas.add(sucursal);
				etiquetas.add(gerente);
				
				//TODO Crear servicios para bancos
				//List<Servicios> listaServicios = new ArrayList<Servicios>();
				
				agregarBanco(new SucursalBanco(etiquetas, posicion));
			}
			
			connection.disconnect();
		}
	
	
	private void agregarBanco(SucursalBanco sucursalBanco) {
		bancos.add(sucursalBanco);
	}
	
	private void agregarPOI (SucursalBanco sucursalBanco){
		Pois.add(sucursalBanco);
	}

	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	

	}


