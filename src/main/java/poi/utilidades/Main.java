package poi.utilidades;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.controllers.ABMcontroller;
import poi.controllers.ConsultaDisponibilidadController;
import poi.controllers.HomeController;
import poi.controllers.LoginController;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.RepositorioAdministrador;
import poi.modelo.usuario.RepositorioTerminal;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioPOI;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		HomeController home = new HomeController();
		LoginController login = new LoginController();
		ConsultaDisponibilidadController consultaDisponibilidad = new ConsultaDisponibilidadController();
		ABMcontroller abm = new ABMcontroller();
		
		port(8080);

		staticFileLocation("/public");
		
//		after((request, responese) -> { 
//			PerThreadEntityManagers.getEntityManager(); 
//			PerThreadEntityManagers.closeEntityManager();
//		});
		
		CargaInicial.getInstance().inicializar();
		
//		usr.persistir(usr);
		
		/** GETs **/
		get("/", home::mostrar, engine);
		get("/index.html", (request, response) -> {
			response.redirect("/");
			return null;
		});
		get("/inicioSesion", login::validarUsrYPass, engine);
		get("/consultaDisponibilidad", consultaDisponibilidad::listar, engine);
	}	
}
