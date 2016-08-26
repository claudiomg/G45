package poi.procesos;

import java.util.ArrayList;
import java.util.List;

import poi.utilidades.Calculo;
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
	List<UsuarioPOI> userGlobal = null ;
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
			List<UsuarioPOI> toRemove = filterByPosicion(user, unValor);
			user.removeAll(toRemove);
			return user;
		}
	}
	
	public List<UsuarioPOI> filterByPosicion(List<UsuarioPOI> listaUser, List<List<Posicion>> listaComuna){
		List<UsuarioPOI> toRemove = new ArrayList<UsuarioPOI>();
		
		for(UsuarioPOI user: listaUser){
		Terminal terminal = new Terminal(user.getUsuario());
		

		for(List<Posicion> cordenada: listaComuna){
		    if(poi.utilidades.Calculo.coordenadasEnComuna(cordenada, terminal.getPosicion())){}
		    else{
		        toRemove.add(user);
		    }
		}
	}
		
	
		return toRemove;		
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