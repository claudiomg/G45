package poi.controllers;



import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UpdatePoisController {

	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "poiAdministrator.html");
	}
}
