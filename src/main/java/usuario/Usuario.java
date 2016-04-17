package usuario;

import java.util.ArrayList;
import java.util.List;

import poi.POI;

public class Usuario {
	private Posicion posicion;
	private static List<Consulta> consultas = new ArrayList<Consulta>();

	public Usuario() {
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public boolean estoyCercaDe(POI poi) {
		Consulta unaConsulta = new Consulta();
		consultas.add(unaConsulta);
		return unaConsulta.sonCercanos(posicion, poi);
	}

}
