package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.RepositorioTerminal;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultaPorCercaniaController {
	
	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("longitud", -58.465881);
		viewModel.put("latitud", -34.670741);
		return new ModelAndView(viewModel, "busquedaPorCercania.html");
	}
	
	public ModelAndView search(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		Terminal usr = RepositorioTerminal.getInstance().terminales.get(0);
		Double longitud = new Double(request.queryParams("longitud"));
		Double latitud = new Double(request.queryParams("latitud"));
		usr.setPosicion(new Posicion(latitud, longitud));
		Consulta unaConsulta = new Consulta(RepositorioPOI.getInstance());
		//unaConsulta.setRepoExterno(RepositorioCGPExternos.getInstance());
		List<POI> poisTotales = RepositorioPOI.getInstance().pois;
		usr.agregarConsulta(unaConsulta,LocalDate.now());
		//usr.buscarPOIPorPalabra(palabraBuscada);
		List<POI> results = new ArrayList<POI>();
		results.addAll(poisTotales.stream().filter( p -> usr.estoyCercaDe(p)).collect(Collectors.toList()));
		//results.addAll(usr.resultados());
		viewModel.put("longitud", longitud.toString());
		viewModel.put("latitud", latitud.toString());
		viewModel.put("resultados", results);
		return new ModelAndView(viewModel, "busquedaPorCercania.html");
	}
	
}
