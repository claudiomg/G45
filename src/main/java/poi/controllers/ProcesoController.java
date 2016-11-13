package poi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.procesos.ProcesoAgregarAccionesAUsuarios;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ProcesoController implements WithGlobalEntityManager{
	
	public ModelAndView render(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();

		return new ModelAndView(viewModel, "activarProcesos.html");
	}

	public ModelAndView agregarAcciones(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();

		List<UsuarioPOI> users = entityManager().createQuery("from UsuarioPOI",UsuarioPOI.class).getResultList(); 

		viewModel.put("results", this.convertUsers(users));
		return new ModelAndView(viewModel, "procesoAgregarAcciones.html");
	}

	public ModelAndView updateActions(Request request, Response response) {
		 HashMap<String, Object> viewModel = new HashMap<>();

		ProcesoAgregarAccionesAUsuarios proceso = new ProcesoAgregarAccionesAUsuarios();
		
		Terminal terminal = entityManager()
				.createQuery("SELECT DISTINCT t from Terminales t, UsuarioPOI u where u.UsuarioId = t.UsuarioId AND u.usuario like :nombre", Terminal.class)
				.setParameter("nombre", "%" + request.queryParams("user") + "%").getSingleResult();
				

		
		proceso.actualizarPermisos(terminal, !(request.queryParams("actionTag") == null),
				!(request.queryParams("actionDisponibility") == null),
				!(request.queryParams("actionProximity") == null ));

		List<UsuarioPOI> users = entityManager().createQuery("from UsuarioPOI",UsuarioPOI.class).getResultList(); 
		
		String mensaje = "Terminal actualizada correctamente.";
		viewModel.put("results", this.convertUsers(users));
		viewModel.put("message", mensaje);
		
		return new ModelAndView(viewModel, "procesoAgregarAcciones.html");
	}

	private List<HashMap<String, Object>> convertUsers(List<UsuarioPOI> users) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();
		for (UsuarioPOI user : users) {
			HashMap<String, Object> element = new HashMap<String, Object>();

			element.put("nombre", user.getUsuario());

			array.add(element);
		}
		return array;
	}

}
