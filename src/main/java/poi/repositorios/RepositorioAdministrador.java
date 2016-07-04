package poi.repositorios;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.usuario.Administrador;

public class RepositorioAdministrador {

	public static RepositorioAdministrador instance = null;
	public List<Administrador> administradores = new ArrayList <Administrador>(); 

	private RepositorioAdministrador(){		
	};

	public static RepositorioAdministrador getInstance() {
		if(instance == null) {
			instance = new RepositorioAdministrador();
		}
		return instance;
	}
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

}
