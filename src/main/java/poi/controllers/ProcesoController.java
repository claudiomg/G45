package poi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.UsuarioPOI;
import poi.procesos.ProcesoAgregarAccionesAUsuarios;
import poi.repositorios.RepositorioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ProcesoController {

	public ModelAndView render(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();

		return new ModelAndView(viewModel, "activarProcesos.html");
	}

	public ModelAndView agregarAcciones(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();

		List<UsuarioPOI> users = RepositorioUsuarios.getInstance().getTerminals();

		viewModel.put("results", this.convertUsers(users));
		return new ModelAndView(viewModel, "procesoAgregarAcciones.html");
	}

	public ModelAndView updateActions(Request request, Response response) {
		// HashMap<String, Object> viewModel = new HashMap<>();

		ProcesoAgregarAccionesAUsuarios proceso = new ProcesoAgregarAccionesAUsuarios();
		
		String sasdf = request.queryParams("user");
		
		UsuarioPOI terminal = RepositorioUsuarios.getInstance().getRegistros().stream()
				.filter(user -> user.getUsuario().equals(request.queryParams("user"))).findFirst().get();

		proceso.actualizarPermisos(terminal, !(request.queryParams("actionTag").isEmpty()),
				!(request.queryParams("actionDisponibility").isEmpty()),
				!(request.queryParams("actionProximity").isEmpty()));


		return new ModelAndView(null, "procesoAgregarAcciones.html");
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
