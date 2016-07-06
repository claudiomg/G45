package poi.controllers;

import java.util.HashMap;

import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController{ //implements WithGlobalEntityManager{
	
	public ModelAndView validarUsrYPass(Request request, Response response){
		
		String userName = request.queryParams("user");
		String password = request.queryParams("password");
		UsuarioPOI usuario;
		HashMap<String, Object> viewModel = new HashMap<>();
		
		usuario = RepositorioUsuarios.getInstance().authenticateUser(userName,password);
		
		if (!(usuario == null)){
			this.createSession(request,usuario);
			if (usuario.isAdmin()){
				return new ModelAndView(viewModel, "adminIndex.html");
			} else {
				return new BuscadorDePoiController().render(request, response);
			}
		}else{
			return new ModelAndView(viewModel, "loginFail.html");
		}

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

	private void createSession(Request request, UsuarioPOI usuario) {
		request.session(true);
		request.session().attribute("user", usuario);
	}
}
