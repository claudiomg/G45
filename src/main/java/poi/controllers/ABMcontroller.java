package poi.controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ABMcontroller {
	
	public ModelAndView mostrar(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "abmPois.html");
	}

}