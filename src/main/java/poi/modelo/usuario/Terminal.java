package poi.modelo.usuario;

import java.util.HashMap;

import poi.utilidades.Posicion;

public class Terminal extends UsuarioPOI{
	
	private Posicion posicion;
	private HashMap<String,Boolean> acciones ;
	
	public Terminal() {
		this.inicializarAcciones();
	}

	private void inicializarAcciones() {
		this.agregarAccion("filterByTag",true);
		this.agregarAccion("filterByDisponibility",true);
		this.agregarAccion("filterByProximity",true);
	}
	
	public void enableFilterByTag() {
		this.agregarAccion("filterByTag",true);
	}
	public void enableFilterByDisponibility() {
		this.agregarAccion("filterByDisponibility",true);
	}
	public void enableFilterByProximity() {
		this.agregarAccion("filterByProximity",true);
	}
	
	public void disableFilterByTag() {
		this.agregarAccion("filterByTag",false);
	}
	public void disableFilterByDisponibility() {
		this.agregarAccion("filterByDisponibility",false);
	}
	public void disableFilterByProximity() {
		this.agregarAccion("filterByProximity",false);
	}
	
	public boolean canFilterByTag() {
		//Modificar cuando este la parte de manejo de acciones
		return this.getAccion("filterByTag");
	}
	public boolean canFilterByDisponibility() {
		//Modificar cuando este la parte de manejo de acciones
		return this.getAccion("filterByDisponibility");
	}

	public boolean canFilterByProximity() {
		//Modificar cuando este la parte de manejo de acciones
		return this.getAccion("filterByProximity");
	}

	private void agregarAccion(String key, boolean bool) {
		this.acciones.put(key, bool);
	}
	
	private boolean getAccion(String key) {
		return this.acciones.get(key);
	}

	
	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public HashMap<String,Boolean> getAcciones() {
		return acciones;
	}
}
