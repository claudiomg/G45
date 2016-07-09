package poi.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poi.finders.PoiFinder;
import poi.finders.PoiFinderBuilder;
import poi.finders.RequestMediator;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Terminal;
import poi.utilidades.Posicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {
	public ModelAndView render(Request request, Response response){
		RequestMediator requestMediator = new RequestMediator(request);
		HashMap<String, Object> viewModel = this.configViewModel(requestMediator);
		viewModel.put("hasResults", false);
		return new ModelAndView(viewModel, "terminalHome.html");
	}

	public ModelAndView search(Request request, Response response){
		RequestMediator requestMediator = new RequestMediator(request);
		PoiFinder finder = new PoiFinderBuilder(requestMediator).buildFinder();
		finder.search();
		HashMap<String, Object> viewModel = this.configViewModel(requestMediator);
		viewModel.put("hasResults", !finder.getResults().isEmpty());
		viewModel.put("results", this.convertPoi(finder.getResults(),request));
		return new ModelAndView(viewModel, "terminalHome.html");
	}
	private HashMap<String, Object> configViewModel(RequestMediator request) {
		HashMap<String, Object> viewModel = new HashMap<>();
		Terminal usuario = request.user();
	//defino valores correspondientes al usuario
		viewModel.put("accessDisponibilityFilter", usuario.canFilterByDisponibility());
		viewModel.put("accessProximityFilter", usuario.canFilterByProximity());
		viewModel.put("accessTagFilter", usuario.canFilterByTag());
		viewModel.put("latitud", usuario.getPosicion().getLatitud());
		viewModel.put("longitud", usuario.getPosicion().getLongitud());
	//defino valores para la vista
		//defino tipos
		viewModel.put("cgpFilterValue", request.customValueForQueryParam("cgpFilter","ON","checked",""));
		viewModel.put("busStopFilterValue", request.customValueForQueryParam("busStopFilter","ON","checked",""));
		viewModel.put("localFilterValue", request.customValueForQueryParam("localFilter","ON","checked",""));
		viewModel.put("bankFilterValue", request.customValueForQueryParam("bankFilter","ON","checked",""));
		//defino acciones
		viewModel.put("disponibilityFilterValue", request.customValueForQueryParam("disponibilityFilter","ON","checked",""));
		viewModel.put("proximityFilterValue", request.customValueForQueryParam("proximityFilter","ON","checked",""));
		viewModel.put("tagFilterValue", request.queryParamOrDefault("tagFilter",""));
		return viewModel;
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
