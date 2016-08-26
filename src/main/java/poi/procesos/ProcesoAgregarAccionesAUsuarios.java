package poi.procesos;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.DarDeBaja;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioPOIsADarDeBaja;
import poi.repositorios.RepositorioUsuarios;
import poi.utilidades.Posicion;

public class ProcesoAgregarAccionesAUsuarios {

	public ProcesoAgregarAccionesAUsuarios() {
	}

	RepositorioUsuarios terminales = RepositorioUsuarios.getInstance();
	List<UsuarioPOI> user = null ;
	List<Posicion> comuna = null;
	List<List<Posicion>> CiudadAutonomaBsAs = null;
	

	
	public List<Posicion> setComuna(){
		//TODO: hacer

		return comuna;
	}
	
	public List<List<Posicion>> setCiudadAutonomaBsAs(List<Posicion> unaComuna){
		//TODO: hacer
		
		return CiudadAutonomaBsAs;
	}
	
	public List<UsuarioPOI> crearActualizacion(List<List<Posicion>> unValor) {
		List<UsuarioPOI> user = terminales.getTerminals();
		if (unValor.isEmpty()) {
			return user;
		} else {
			List<UsuarioPOI> userenComuna = user;//removeIf(noestaTerminalEncomuna());
			// TODO: Filtrar usuarios si estan o no en comuna!
			return userenComuna;
		}
	}

	public void actualizarPermisos(UsuarioPOI user, boolean permisoEtiqueta, boolean permisoDisponibilidad,
			boolean permisoCercania) {
		Terminal terminal = new Terminal(user.getUsuario());

		terminal.updateFilterByTag(permisoEtiqueta);
		terminal.updateFilterByDisponibility(permisoDisponibilidad);
		terminal.updateFilterByDisponibility(permisoCercania);
	}

	public void correrProceso(List<List<Posicion>> unValor,  boolean permisoEtiqueta, boolean permisoDisponibilidad, boolean permisoCercania) throws Exception {
		for (UsuarioPOI elemento : crearActualizacion(unValor)) {
			 actualizarPermisos(elemento, permisoEtiqueta,permisoDisponibilidad,
						permisoCercania);
		}
	}
}