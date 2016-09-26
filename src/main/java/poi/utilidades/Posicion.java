package poi.utilidades;

import javax.persistence.*;

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
