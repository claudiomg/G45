package controller;

import java.util.HashMap;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class LoginController implements WithGlobalEntityManager{
	
	public ModelAndView validarUsrYPass(Request request, Response response){
		
		String nombreUsuario = request.queryParams("user");
		String passwordUsuario = request.queryParams("password");
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		if( (entityManager() 
				.createQuery("from Usuario u where u.login like :login and u.password = :password", Usuario.class) 
				.setParameter("login", "%" + nombreUsuario + "%") //
				.setParameter("password", passwordUsuario)
				.getResultList().size()) != 0){	
			return new ModelAndView(viewModel, "loginSuccess.html");
		}
		else {
			return new ModelAndView(viewModel, "loginFail.html");
		}	
	}
}
