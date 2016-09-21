package poi.modelo.usuario;

import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import poi.utilidades.Posicion;

@Entity(name = "Terminales")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Terminal extends UsuarioPOI{
	@OneToOne(cascade = CascadeType.PERSIST
			, fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private Posicion posicion;
	@OneToMany(cascade = CascadeType.PERSIST
			, fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private HashMap<String,Boolean> acciones = new HashMap<String,Boolean>();
	
	public Terminal(String usuario) {
		this.setUsuario(usuario);
		this.inicializarAcciones();
	}

	private void inicializarAcciones() {
		this.agregarAccion("filterByTag",true);
		this.agregarAccion("filterByDisponibility",true);
		this.agregarAccion("filterByProximity",true);
	}
	
	public void updateFilterByTag(boolean value) {
		this.updateAccion("filterByTag",value);
	}
	public void updateFilterByDisponibility(boolean value) {
		this.updateAccion("filterByDisponibility",value);
	}

	public void updateFilterByProximity(boolean value) {
		this.updateAccion("filterByProximity",value);
	}
	public void updateAccion(String key, boolean value) {
		acciones.put(key, value);
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
