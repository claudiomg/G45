package poi.controllers;



import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {

	public ModelAndView mostrarABM(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "abmPois.html");
	}
	
	public ModelAndView mostrarReporte(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "reportes.html");
	}
	
	public ModelAndView mostrarAdmTerminal(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "adminTerminales.html");
	}
		
	
}
