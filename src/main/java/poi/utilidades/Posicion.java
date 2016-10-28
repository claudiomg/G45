package poi.utilidades;

import javax.persistence.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Entity
@Table(name = "Posiciones")
public class Posicion {
	@Id
	@GeneratedValue
	@Column(name = "PosicionId")
	private Long PosicionId;
	public Double latitud;
	public Double longitud;
	
	public Posicion() {
	}
	
	public Posicion(Double latitud, Double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Posicion(JSONObject locationJson) {
		try {
			this.longitud = new Double(locationJson.getString("lng"));
			this.latitud = new Double(locationJson.getString("lat"));
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
	}

	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
}
