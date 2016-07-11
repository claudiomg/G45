package poi.controllers;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioPOI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UpdatePoisController {

	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		List<POI> pois = RepositorioPOI.getInstance().getRegistros();
		
		viewModel.put("hasResults", !pois.isEmpty());
		viewModel.put("results", this.convertPois(pois));
		return new ModelAndView(viewModel, "poiAdministrator.html");
	}	
	
	public ModelAndView modificar(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		
		return new ModelAndView(viewModel, "modificacionPOI.html");
	}
	
	public ModelAndView agregar(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		
		
		return new ModelAndView(viewModel, "modificacionPOI.html");
	}
	
	private List<HashMap<String, Object>> convertPois(List<POI> pois) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		for ( POI poi : pois){
			HashMap<String,Object> element = new HashMap<String,Object>();
			
			element.put("nombre", poi.getNombre());
			
			array.add(element);
		}
		return array;
}
}
