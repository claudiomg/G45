package poi.controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class QueryReportsController {
	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView reportByDate(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView reportByTerminal(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView reportByTerminalAndDate(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "queryReports.html");
	}
}
