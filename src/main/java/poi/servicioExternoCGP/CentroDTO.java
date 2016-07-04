package poi.servicioExternoCGP;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {

	private int numeroComuna;
	private String nombre;
	private double longitud;
	private double latitud;
	private String zonasIncluidas;
	private String nombreDirector;
	private String domicilio;
	private String telefono;
	private List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	
	public CentroDTO(int numeroComuna, String zonasIncluidas, String nombreDirector, String domicilio, String telefono,
			List<ServicioDTO> servicios) {
		this.numeroComuna = numeroComuna;
		this.zonasIncluidas = zonasIncluidas;
		this.nombreDirector = nombreDirector;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.servicios = servicios;
	}
	
	public int getNumeroComuna() {
		return numeroComuna;
	}
	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}
	public String getZonasIncluidas() {
		return zonasIncluidas;
	}
	public void setZonasIncluidas(String zonasIncluidas) {
		this.zonasIncluidas = zonasIncluidas;
	}
	public String getNombreDirector() {
		return nombreDirector;
	}
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<ServicioDTO> getServicios() {
		return servicios;
	}
	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
}
