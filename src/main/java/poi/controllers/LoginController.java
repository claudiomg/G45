package poi.controllers;

import java.util.HashMap;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.usuario.RepositorioAdministrador;
import poi.modelo.usuario.RepositorioTerminal;
import poi.modelo.usuario.Terminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController{ //implements WithGlobalEntityManager{
	
	public ModelAndView validarUsrYPass(Request request, Response response){
		
		String nombreUsuario = request.queryParams("user");
		String passwordUsuario = request.queryParams("password");
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		if (RepositorioTerminal.getInstance().terminales.stream()
				.anyMatch(termi->(termi.getLogin().equals(nombreUsuario) && termi.getPassword().equals(passwordUsuario)))){
			return new ModelAndView(viewModel, "terminalIndex.html");
		} else if (RepositorioAdministrador.getInstance().administradores.stream()
				.anyMatch(adm->(adm.getLogin().equals(nombreUsuario) && adm.getPassword().equals(passwordUsuario)))) {
			return new ModelAndView(viewModel, "adminIndex.html");
		} else {
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
}
