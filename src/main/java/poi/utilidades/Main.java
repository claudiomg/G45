package poi.utilidades;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.controllers.AdministratorController;
import poi.controllers.TerminalController;
import poi.controllers.LoginController;
import poi.controllers.ReportePorFechaController;
import poi.controllers.ReportePorFechaYTerminalController;
import poi.controllers.ReportePorTerminalController;
import poi.controllers.TerminalConfigurationController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8080);

		staticFileLocation("/public");
		
		CargaInicial.getInstance().inicializar();
		
		// INDEX PRINCIPAL - LOGIN
		LoginController login = new LoginController();
		get("/", login::render, engine);
		get("/index.html", (request, response) -> {
			response.redirect("/",200);
			return null;
		});
		post("/login_process", (request, response) -> {
		    return login.validarUsrYPass(request, response);
		});
		get("/my_home_page", login::redirectUser, engine);//get controla si hay usuario logueado sino hay lo envia al login
		
		
		//HOME DE TERMINAL
		TerminalController poiBrowser = new TerminalController();
		post("/my_home_page", poiBrowser::search, engine);//post es usado para las busquedas
		

		//HOME DE ADMINISTRADOR
		AdministratorController admin = new AdministratorController();
		get("/poi_administrator",admin::mostrarABM,engine);
		get("/query_reports", admin::viewQueryReports, engine);
		
		//configuracion de terminales
		TerminalConfigurationController terminalConfiguration = new TerminalConfigurationController();
		get("/terminal_configuration", terminalConfiguration::render, engine);
		post("/update_terminal_configuration", terminalConfiguration::updateConfiguration, engine);
		
		//REPORTES
		ReportePorFechaController reporteFecha = new ReportePorFechaController();
		get("/BusquedaPorFecha", reporteFecha::mostrar, engine);
		ReportePorTerminalController reporteTerminal = new ReportePorTerminalController();
		get("/BusquedaPorTerminal", reporteTerminal::mostrar, engine);
		ReportePorFechaYTerminalController reporteFechaTerminal = new ReportePorFechaYTerminalController();
		get("/BusquedaPorFechaYTerminal", reporteFechaTerminal::mostrar,engine);
	}
	 
}
