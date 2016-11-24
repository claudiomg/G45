package poi.utilidades;

import static spark.Spark.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jetty.http.HttpTester.Request;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import poi.controllers.UpdatePoisController;
import poi.dataImport.BankImport;
import poi.dataImport.BankImportService;
import poi.dataImport.ColectivoImportService;
import poi.dataImport.DataImportService;
import poi.dataImport.KioscoImportService;
import poi.dataImport.LibraryImportService;
import poi.servicioRest.JsonTransformer;
import poi.servicioRest.ResponseError;
import poi.servicioRest.ServicioRest;
import poi.controllers.TerminalController;
import poi.controllers.ConfigAccionesController;
import poi.controllers.HistorialDeBusquedasController;
import poi.controllers.LoginController;
import poi.controllers.ProcesoController;
import poi.controllers.QueryReportsController;
import poi.controllers.TerminalConfigurationController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main extends AbstractPersistenceTest implements WithGlobalEntityManager{
	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8080);

		staticFileLocation("/public");
		
		
		//new BankImportService().importData();
		//new LibraryImportService().importData();
		//new KioscoImportService().importData();
		//new ColectivoImportService().importData();
		//new BankImport().importData("/home/juan/DDS/eclipse/G45/src/main/resources/public/dataImport/bank.csv",";");
		
		TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
		TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
		ExcepcionHorarioCambiado excepcion = new ExcepcionHorarioCambiado(LocalDate.now());
		excepcion.agregarRangoCambiado(rangoInferiorDeLaSemana);
		excepcion.agregarRangoCambiado(rangoSuperiorDeLaSemana);
		excepcion.persistir(excepcion);
		
		
//		/** CREAR DIRECCION PERSISTIBLE **/
//		Direccion dir = new Direccion();
//		dir.setBarrio("Almagro");
//		dir.setLocalidad("Chivilcoy");
//		dir.setCalle("Guemes");
//		dir.setCodigoPostal("2222");
//		dir.persistir(dir);
//		/** BORRAR **/
//
//		/** CREAR DISPONIBILIDAD PERSISTIBLE **/		
//		TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
//		TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
//		DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
//		lunes.agregarNuevoRango(rangoInferiorDeLaSemana);
//		lunes.agregarNuevoRango(rangoSuperiorDeLaSemana);
//		lunes.persistir(lunes);
//		/** BORRAR **/
				
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
		//ABM POIS
		UpdatePoisController admin = new UpdatePoisController();
		get("/poi_administrator",admin::render,engine);
		get("/modificar",admin::modificar,engine);
		get("/agregar",admin::agregar,engine);
		get("/excepciones",admin::excepcion,engine);
		
				
		//REPORTS
		QueryReportsController queryReports = new QueryReportsController();
		get("/query_reports", queryReports::render, engine);
			get("/query_reports/byDate", queryReports::reportByDate, engine);
			get("/query_reports/byTerminal", queryReports::reportByTerminal, engine);
			get("/query_reports/byTerminalAndDate", queryReports::reportByTerminalAndDate, engine);
			
		//CONFIG TERMINALS
		TerminalConfigurationController terminalConfiguration = new TerminalConfigurationController();
		get("/terminal_configuration", terminalConfiguration::render, engine);
		post("/update_terminal_configuration", terminalConfiguration::updateConfiguration, engine);
		
		//PROCESOS
		ProcesoController process = new ProcesoController();
		get("/activarProcesos", process::render, engine);
			post("/proceso/agregarAcciones", process::updateActions, engine);
			get("/proceso/agregarAcciones", process::agregarAcciones, engine);
			//put("/proceso/agregarAcciones", process::updateActions, engine);
			
		//HISTORIAL DE BUSQUEDA
		HistorialDeBusquedasController historial = new HistorialDeBusquedasController();
		get("/historial_busquedas", historial::render, engine );
		get("/armar-tabla", historial::mostrarLista, engine);
		
		
			
		//CONFIG ACCIONES EN CONSULTA
		ConfigAccionesController configAcciones = new ConfigAccionesController();
		get("/configAccionesXUsuario",configAcciones::render, engine);
		get("/configAcciones",configAcciones::renderUsuario, engine);
		post("/configAcciones",configAcciones::agregarAcciones, engine);
		get("/configAcciones_Eliminar", configAcciones::eliminarAcciones, engine);
	
		//REST
		get("/consultas/:usuario", (request, response) -> {
			String terminal = request.params(":usuario");
			List<HashMap<String, Object>> listado = ServicioRest.getInstance().restBusquedaUsuario(terminal); 
			if (listado != null){
				return listado;
			}			
			response.status(400);
			return new ResponseError("No se encuentra el usuario '%s'", terminal);		    		
		}, new JsonTransformer());		
	
		get("/consultas/:finicio/:ffin", (request, response) -> {
			String finicio = request.params(":finicio");
			String ffin = request.params(":ffin");			
			List<HashMap<String, Object>> listado = ServicioRest.getInstance().restBusquedaFechas(finicio, ffin); 
			if (listado != null){
				return listado;
			}			
			response.status(400);
			return new ResponseError("No hay consultas entre las fechas indicadas");		    		
		}, new JsonTransformer());		
	
		
		get("/pois/porNombre/:nombre",(request,response) -> {
			String nombre = request.params(":nombre");
			List<HashMap<String,Object>> listado = ServicioRest.getInstance().restBusquedaPOIsPorNombre(nombre);
			if (listado != null){
				return listado;
			}
			response.status(400);
			return new ResponseError("No hay pois con ese nombre");
			},new JsonTransformer());
		
		get("/pois/porCalle/:calle",(request,response) -> {
			String calle = request.params(":calle");
			List<HashMap<String,Object>> listado = ServicioRest.getInstance().restBusquedaPOIsPorCalle(calle);
			if(listado != null ){
				return listado;
			}
			response.status(400);
			return new ResponseError("No hay pois con esa palabra clave");
			},new JsonTransformer());
		
		get("/pois/porCalleYNombre/:calle/:nombre",(request,response) -> {
			String calle = request.params(":calle");
			String nombre = request.params(":nombre");
			List<HashMap<String,Object>> listado = ServicioRest.getInstance().restBusquedaPOIsPorCalleYNombre(calle,nombre);
			if(listado != null ){
				return listado;
			}
			response.status(400);
			return new ResponseError("No hay pois con esa palabra clave");
			},new JsonTransformer());
	}	 
	
 	

}
