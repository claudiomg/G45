package poi.controllers;



import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministratorController {

	public ModelAndView mostrarABM(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "poiAdministrator.html");
	}
	
	public ModelAndView viewQueryReports(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView mostrarAdmTerminal(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "terminalConfiguration.html");
	}
		
	
}
