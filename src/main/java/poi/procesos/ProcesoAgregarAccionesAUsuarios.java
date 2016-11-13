package poi.procesos;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import poi.utilidades.Posicion;

public class ProcesoAgregarAccionesAUsuarios implements WithGlobalEntityManager{

	public ProcesoAgregarAccionesAUsuarios() {
	}

	private RepositorioUsuarios terminales = RepositorioUsuarios.getInstance();

	private List<Posicion> comuna = null;
	private List<List<Posicion>> CiudadAutonomaBsAs = null;

	public List<Posicion> setComuna() {
		// TODO: hacer

		return comuna;
	}

	public List<List<Posicion>> setCiudadAutonomaBsAs(List<Posicion> unaComuna) {
		// TODO: hacer

		return CiudadAutonomaBsAs;
	}

	public List<Terminal> crearActualizacion(List<List<Posicion>> unValor) {
		List<Terminal> user = entityManager().createQuery("from Terminal t ",Terminal.class).getResultList(); 
		
		if (unValor.isEmpty()) {
			return user;
		} else {
			List<Terminal> toRemove = filterByPosicion(user, unValor);
			user.removeAll(toRemove);
			return user;
		}
	}

	public List<Terminal> filterByPosicion(List<Terminal> listaUser, List<List<Posicion>> listaComuna) {
		List<Terminal> toRemove = new ArrayList<Terminal>();

		for (Terminal user : listaUser) {

			for (List<Posicion> cordenada : listaComuna) {
				if (poi.utilidades.Calculo.coordenadasEnComuna(cordenada, user.getPosicion())) {
				} else {
					toRemove.add(user);
				}
			}
		}

		return toRemove;
	}

	public void actualizarPermisos(Terminal terminal, boolean permisoEtiqueta, boolean permisoDisponibilidad,
			boolean permisoCercania) {

		terminal.updateFilterByTag(permisoEtiqueta);
		terminal.updateFilterByDisponibility(permisoDisponibilidad);
		terminal.updateFilterByDisponibility(permisoCercania);
	}

	public void correrProceso(List<List<Posicion>> unValor, boolean permisoEtiqueta, boolean permisoDisponibilidad,
			boolean permisoCercania) throws Exception {
		try {
			for (Terminal elemento : crearActualizacion(unValor)) {
				actualizarPermisos(elemento, permisoEtiqueta, permisoDisponibilidad, permisoCercania);
			}
		} catch (Exception e) {
			System.out.println("Error al buscar terminal");
		}
	}
}