package poi.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import poi.modelo.usuario.Terminal;
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
	
	public List<UsuarioPOI> getAdministradores() {
		return this.getRegistros()
			.stream()
			.filter(admin -> admin.isAdmin())
			.collect(Collectors.toList());
	}
	
	public UsuarioPOI authenticateUser(String userName, String password) {
		for ( UsuarioPOI user : this.getRegistros()){
			if (user.hasAccess(userName,password)){
				return user;
			}
		}
		return null;
	}
	public List<UsuarioPOI> getTerminals() {
		return this.getRegistros()
				.stream()
				.filter( terminal -> !terminal.isAdmin())
				.collect(Collectors.toList());
	}
	
}
