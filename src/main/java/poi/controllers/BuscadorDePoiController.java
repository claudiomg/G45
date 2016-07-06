package poi.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import poi.finders.PoiFinder;
import poi.finders.PoiFinderBuilder;
import poi.finders.RequestMediator;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Terminal;
import poi.utilidades.Posicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BuscadorDePoiController {
	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("results", false);
		return new ModelAndView(viewModel, "terminalHome.html");
	}
	
	public ModelAndView search(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		RequestMediator requestMediator = new RequestMediator(request);
		PoiFinder finder = new PoiFinderBuilder(requestMediator).buildFinder();
		finder.search();
		viewModel.put("hasResults", !finder.getResults().isEmpty());
		viewModel.put("results", this.convertPoi(finder.getResults(),request));
		viewModel.put("string", this.convertPoi(finder.getResults(),request).toString());
		return new ModelAndView(viewModel, "terminalHome.html");
	}

	private JsonArray convertPoi(List<POI> results,Request request) {
		Posicion posicion = ((Terminal) request.session().attribute("user")).getPosicion();
		LocalDateTime fechaHora = LocalDateTime.now();
		JsonArray array = new JsonArray();
		for ( POI punto : results){
			JsonObject element = new JsonObject();
			element.addProperty("nombre", punto.getNombre());
			element.addProperty("latitud", punto.getPosicion().getLatitud());
			element.addProperty("longitud", punto.getPosicion().getLongitud());
			element.addProperty("calle", punto.getPosicion().getLongitud());
			element.addProperty("altura", punto.getPosicion().getLongitud());
			element.addProperty("estaCerca", punto.estaCercaDe(posicion));
			element.addProperty("estaDisponible", punto.estaDisponible(fechaHora));
			array.add(element);
		}
		return array;
	}

}
