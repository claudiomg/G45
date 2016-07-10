package poi.controllers;

import java.util.HashMap;

import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController{
	
	public ModelAndView render(Request request, Response response){
		return new ModelAndView(new HashMap<>(), "index.html");
	}
	
	public String validarUsrYPass(Request request, Response response){
		//se encarga de validar el usuario y contraseña si puede loguear crea la session y devuelve ok
		this.autenticationUser(request);
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			return "El usuario y la contraseña que has introducido no coinciden.";
		}
		return "ok";
	}
	
	public ModelAndView redirectUser(Request request, Response response){
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return new ModelAndView(new HashMap<>(), "index.html");
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		if (user.isAdmin()){
			return new ModelAndView(viewModel, "administratorHome.html");
		} else {
			return new TerminalController().render(request, response);
		}
	}

	private void autenticationUser(Request request) {
		String userName = request.queryParams("user");
		String password = request.queryParams("password");
		UsuarioPOI usuario = RepositorioUsuarios.getInstance().authenticateUser(userName,password);
		request.session(true);
		request.session().attribute("user", usuario);
	}
}
