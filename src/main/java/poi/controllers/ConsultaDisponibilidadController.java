package poi.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;

import poi.finders.FilterByDisponibility;
import poi.finders.PoiFinder;
import poi.finders.PoiFinderBuilder;
import poi.finders.RequestMediator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultaDisponibilidadController {

	public ModelAndView listar(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();
		RequestMediator requestMediator = new RequestMediator(request);
		PoiFinder finder = new PoiFinderBuilder(requestMediator).buildFinder();
		finder.addFilter(new FilterByDisponibility(LocalDateTime.now()));
		finder.search();
		viewModel.put("results", finder.getResults());
		viewModel.put("poisTotales", finder.getRootObjects());
		viewModel.put("poisDisponibles", finder.getResults());
		viewModel.put("cantidadDisponibles", finder.getResults().size());
		viewModel.put("cantidadTotales", finder.getRootObjects().size());
		return new ModelAndView(viewModel, "consultaDisponibilidad.html");
	}

}
