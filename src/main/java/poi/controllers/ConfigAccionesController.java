package poi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConfigAccionesController implements WithGlobalEntityManager {

	private String userName;
	private Terminal user;
	
	public ModelAndView render(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();

		List<UsuarioPOI> listUsuarios = new ArrayList<UsuarioPOI>();
		listUsuarios = RepositorioUsuarios.getInstance().getTerminals();
		
		viewModel.put("listUsuarios", listUsuarios);
		
		return new ModelAndView(viewModel, "SelectUserConfigAcciones.html");
	}

	public ModelAndView renderUsuario(Request request, Response response) {
		userName = request.queryParams("user");

//		UsuarioPOI user = RepositorioUsuarios.getInstance().getTerminals().stream().filter(unUsuario -> unUsuario.getUsuario().equals(userName)).collect(Collectors.toCollection(ArrayList::new)).get(0);
		user = entityManager()
				.createQuery("SELECT DISTINCT t from Terminales t, UsuarioPOI u where u.UsuarioId = t.UsuarioId AND u.usuario like :nombre", Terminal.class)
				.setParameter("nombre", "%" + userName + "%").getSingleResult();

		HashMap<String, Object> viewModel = new HashMap<>();

		viewModel.put("results", this.getAcciones(user));

		return new ModelAndView(viewModel, "ConfigAccionesEnConsulta.html");
	}

	 public ModelAndView agregarAcciones(Request request, Response response){
		 String accionAAgregar = request.queryParams("accion");
		 
		 user.getAcciones().put(accionAAgregar, true);

		 HashMap<String, Object> viewModel = new HashMap<>();
		 
		 viewModel.put("results", this.getAcciones(user));

			return new ModelAndView(viewModel, "ConfigAccionesEnConsulta.html");
		
		 
	 }
	 
	 public ModelAndView eliminarAcciones(Request request,Response response){
		 String accionAEliminar = request.queryParams("accion");
		 
		 user.getAcciones().remove(accionAEliminar);

		 HashMap<String, Object> viewModel = new HashMap<>();
		 
		 viewModel.put("results", this.getAcciones(user));

			return new ModelAndView(viewModel, "ConfigAccionesEnConsulta.html");
		 
	 }

	public List<String> getAcciones(Terminal user) {

//		HashMap<String, Boolean> accionesDelUsuario = new HashMap<String, Boolean>();
		List<String> accionesAMostrar = new ArrayList<String>();
		

		for (String key : user.getAcciones().keySet()) {
			accionesAMostrar.add(key);
		}

		return accionesAMostrar;

	}
}
