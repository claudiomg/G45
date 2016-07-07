package poi.controllers;

import java.util.HashMap;

import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController{ //implements WithGlobalEntityManager{
	
	public ModelAndView validarUsrYPass(Request request, Response response){
		
		this.autenticationUser(request);
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/login_fail");
			return null;
		}
		response.redirect("/my_home_page");
		return null;

//		if( (entityManager() 
//				.createQuery("from Usuario u where u.login like :login and u.password = :password", Terminal.class) 
//				.setParameter("login", "%" + nombreUsuario + "%") //
//				.setParameter("password", passwordUsuario)
//				.getResultList().size()) != 0){	
//			return new ModelAndView(viewModel, "loginSuccess.html");
//		}
//		else {
//			return new ModelAndView(viewModel, "loginFail.html");
//		}	
	}

	public ModelAndView loginFail(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		return new ModelAndView(viewModel, "loginFail.html");
	}
	
	public ModelAndView redirectUser(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return null;
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
