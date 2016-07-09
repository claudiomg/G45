package poi.controllers;

import java.util.HashMap;

import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController{ //implements WithGlobalEntityManager{
	
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
		HashMap<String, Object> viewModel = new HashMap<>();
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return new ModelAndView(viewModel, "index.html");
		}
		
		if (user.isAdmin()){
			return new ModelAndView(viewModel, "adminIndex.html");
		} else {
			return new BuscadorDePoiController().render(request, response);
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
