package usuario;

import java.util.ArrayList;
import java.util.List;

import poi.POI;

public class Usuario {
	private Posicion posicion;
	private static List<Consulta> consultas = new ArrayList<Consulta>();
	private Consulta consultaActiva;

	public Usuario() {
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public boolean estoyCercaDe(POI poi) {
		return this.consultaActiva.sonCercanos(this.posicion, poi);
	}

	public void buscarPOIPorPalabra(String palabra){
		this.consultaActiva.buscarPorPalabra(palabra);
	}
	
	public Consulta getConsultaActiva() {
		return consultaActiva;
	}

	public void agregarConsulta(Consulta unaConsulta){
		consultas.add(unaConsulta);
		this.consultaActiva = unaConsulta;
	}
}
