package poi.repositorios;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.UsuarioPOI;

public class RepositorioUsuarios {

	protected static RepositorioUsuarios instance;
	protected RepositorioUsuarios() { /*Existe para anular la instanciacion*/ };
	public static RepositorioUsuarios getInstance() {
		if(instance == null) {
			instance = new RepositorioUsuarios();
		}
		return instance;
	}
	
	public List<UsuarioPOI> registros = new ArrayList <UsuarioPOI>(); 

	public void agregarRegistro(UsuarioPOI unRegistro){
		this.registros.add(unRegistro);
	}
	public void eliminarRegistro(UsuarioPOI unRegistro){
		this.registros.remove(unRegistro);
	}
	public void cleanRepository() {
		this.registros = new ArrayList <UsuarioPOI>();
	}
	public List<UsuarioPOI> getRegistros() {
		return this.registros;
	}
	
	@SuppressWarnings("unchecked")
	public List<Administrador> getAdministradores() {
		return (List<Administrador>) this.getRegistros().stream().filter(admin -> admin.isAdmin());
	}
	
}
