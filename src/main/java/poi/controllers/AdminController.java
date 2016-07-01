package poi.controllers;



import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdminController {

	
	public ModelAndView administrar(Request request, Response response){
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		if (request.params("btnAdmPOI") != null){
			return new ModelAndView(viewModel, "abmPois.html");
		} else if (request.params("btnReporte") != null){
			return new ModelAndView(viewModel, "reportesBusquedas.html");
		} else if (request.params("btnAdmTerm") != null){
			return new ModelAndView(viewModel, "adminTerminales.html");
		}
		
		
		
		return null;
	
	}
	
}
