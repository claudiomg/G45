package poi.controllers;

import java.util.HashMap;

import poi.finders.PoiFinder;
import poi.finders.PoiFinderBuilder;
import poi.finders.RequestMediator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BuscadorDePoiController {
	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		return new ModelAndView(viewModel, "terminalHome.html");
	}
	
	public ModelAndView search(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		RequestMediator requestMediator = new RequestMediator(request);
		PoiFinder finder = new PoiFinderBuilder(requestMediator).buildFinder();
		finder.search();
		viewModel.put("results", finder.getResults());
		return new ModelAndView(viewModel, "terminalHome.html");
	}
}
