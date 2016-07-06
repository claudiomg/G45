package poi.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
		Terminal usuario = request.session().attribute("user");
		RequestMediator requestMediator = new RequestMediator(request);
		PoiFinder finder = new PoiFinderBuilder(requestMediator).buildFinder();
		finder.search();
		viewModel.put("latitud", usuario.getPosicion().getLatitud());
		viewModel.put("longitud", usuario.getPosicion().getLongitud());
		viewModel.put("hasResults", !finder.getResults().isEmpty());
		viewModel.put("results", this.convertPoi(finder.getResults(),request));
		return new ModelAndView(viewModel, "terminalHome.html");
	}

	private List<HashMap<String, Object>> convertPoi(List<POI> results,Request request) {
		Posicion posicion = ((Terminal) request.session().attribute("user")).getPosicion();
		LocalDateTime fechaHora = LocalDateTime.now();
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		for ( POI punto : results){
			HashMap<String,Object> element = new HashMap<String,Object>();
			element.put("nombre", punto.getNombre());
			element.put("latitud", punto.getPosicion().getLatitud());
			element.put("longitud", punto.getPosicion().getLongitud());
			element.put("barrio", punto.getDireccion().getBarrio());
			element.put("calle", punto.getDireccion().getCalle() + " " + punto.getDireccion().getNumero());
			element.put("cp", punto.getDireccion().getCodigoPostal());
			element.put("estaCerca", punto.estaCercaDe(posicion));
			element.put("estaDisponible", punto.estaDisponible(fechaHora));
			array.add(element);
		}
		return array;
	}

}
