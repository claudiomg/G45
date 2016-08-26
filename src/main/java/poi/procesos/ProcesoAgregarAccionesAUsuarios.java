package poi.procesos;

import java.util.List;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;

public class ProcesoAgregarAccionesAUsuarios {

	public ProcesoAgregarAccionesAUsuarios() {
	}

	RepositorioUsuarios terminales = RepositorioUsuarios.getInstance();

	public List<UsuarioPOI> crearActualizacion(List<String> unValor) {
		List<UsuarioPOI> user = terminales.getTerminals();
		if (unValor == null) {
			return user;
		} else {
			// TODO: HAACER LO DE COMUNAS
			return user;
		}
	}

	public void actualizarPermisos(UsuarioPOI user, boolean permisoEtiqueta, boolean permisoDisponibilidad,
			boolean permisoCercania) {
		Terminal terminal = new Terminal(user.getUsuario());

		terminal.updateFilterByTag(permisoEtiqueta);
		terminal.updateFilterByDisponibility(permisoDisponibilidad);
		terminal.updateFilterByDisponibility(permisoCercania);
	}

	public void correrProceso(List<String> unValor,  boolean permisoEtiqueta, boolean permisoDisponibilidad, boolean permisoCercania) throws Exception {
		for (UsuarioPOI elemento : crearActualizacion(unValor)) {
			 actualizarPermisos(elemento, permisoEtiqueta,permisoDisponibilidad,
						permisoCercania);
		}
	}
}