package poi.utilidades;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.controllers.ABMcontroller;
import poi.controllers.AdmTerminalesController;
import poi.controllers.BuscadorDePoiController;
import poi.controllers.ConsultaDisponibilidadController;
import poi.controllers.HomeController;
import poi.controllers.LoginController;
import poi.controllers.ReportesController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8080);

		staticFileLocation("/public");
		
//		after((request, responese) -> { 
//			PerThreadEntityManagers.getEntityManager(); 
//			PerThreadEntityManagers.closeEntityManager();
//		});
//		usr.persistir(usr);
		
		// INDEX PRINCIPAL
		HomeController home = new HomeController();
		get("/", home::mostrar, engine);
		get("/index.html", (request, response) -> {
			response.redirect("/");
			return null;
		});
		
		//LOGIN
		LoginController login = new LoginController();
		post("/inicioSesion", login::validarUsrYPass, engine);
		
		
		//INDEX DE TERMINAL
		BuscadorDePoiController poiBrowser = new BuscadorDePoiController();
		get("/terminalHome", poiBrowser::render, engine);
		post("/terminalSearch", poiBrowser::search, engine);
		
		ConsultaDisponibilidadController byDisponibilidad = new ConsultaDisponibilidadController();
		get("/consultaDisponibilidad", byDisponibilidad::listar, engine);
		
		//INDEX DE ADMINISTRADOR
		ABMcontroller abm = new ABMcontroller();
		get("/abmPois",abm::mostrar,engine);
		
		ReportesController reporte = new ReportesController();
		get("/reportes", reporte::mostrar, engine);
		
		AdmTerminalesController admTerminales = new AdmTerminalesController();
		get("/admTerminales", admTerminales::mostrar, engine);
	}
	
}
