package poi.utilidades;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.controllers.ABMcontroller;
import poi.controllers.ConsultaDisponibilidadController;
import poi.controllers.ConsultaPorPalabraController;
import poi.controllers.HomeController;
import poi.controllers.LoginController;
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
		
		CargaInicial.getInstance().inicializar();
		
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
		ConsultaPorPalabraController byPalabra = new ConsultaPorPalabraController();
		post("/consultaPorPalabra", byPalabra::mostrar, engine);
		
		ConsultaDisponibilidadController byDisponibilidad = new ConsultaDisponibilidadController();
		post("/consultaDisponibilidad", byDisponibilidad::listar, engine);
		
		ConsultaPorPalabraController byCercania = new ConsultaPorPalabraController();
		post("/consultaPorCercani", byCercania::mostrar, engine);
		
		//INDEX DE ADMINISTRADOR
		ABMcontroller abm = new ABMcontroller();
	}	
}
