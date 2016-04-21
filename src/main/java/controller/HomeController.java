package controller;

import java.util.HashMap;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class HomeController {
	
	public ModelAndView mostrar(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "index.html");
	}

}
