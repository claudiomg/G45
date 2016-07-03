package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.RepositorioTerminal;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
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
		Terminal usr = RepositorioTerminal.getInstance().terminales.get(0);
		Consulta unaConsulta = new Consulta(RepositorioPOI.getInstance());
		//unaConsulta.setRepoExterno(RepositorioCGPExternos.getInstance());
		String palabraBuscada = request.queryParams("palabra");
		List<POI> poisTotales = RepositorioPOI.getInstance().pois;
		usr.agregarConsulta(unaConsulta,LocalDate.now());
		//usr.buscarPOIPorPalabra(palabraBuscada);
		List<POI> results = new ArrayList<POI>();
		results.addAll(poisTotales);//hardcodeo por que el metodo buscarPOIPorPalabra no funciona 
		//results.addAll(usr.resultados());
		viewModel.put("cantidadTotales", poisTotales.size());
		viewModel.put("poisTotales", poisTotales);
		viewModel.put("cantidadResultados", results.size());
		viewModel.put("resultados", results);
		
		return new ModelAndView(viewModel, "terminalHome.html");
	}
}
