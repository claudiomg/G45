package poi.utilidades;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.controllers.HomeController;
import poi.controllers.LoginController;
import poi.modelo.usuario.Terminal;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Main implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		HomeController home = new HomeController();
		LoginController login = new LoginController();

		port(8080);

		staticFileLocation("/public");
		
		after((request, responese) -> { 
			PerThreadEntityManagers.getEntityManager(); 
			PerThreadEntityManagers.closeEntityManager();
		});

		/** CREAR USR_DEFAULT **/
		Terminal usr = new Terminal();
		
		usr.setLogin("Kevin01");
		usr.setPassword("Abc123");
		usr.persistir(usr);
		
		/** GETs **/
		get("/", home::mostrar, engine);
		get("/index.html", (request, response) -> {
			response.redirect("/");
			return null;
		});
		get("/inicioSesion", login::validarUsrYPass, engine);
		
	}
}
