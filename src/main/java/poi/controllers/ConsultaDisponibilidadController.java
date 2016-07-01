package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.RepositorioTerminal;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultaDisponibilidadController {

	public ModelAndView listar(Request request, Response response) {
		Terminal usr = RepositorioTerminal.getInstance().terminales.get(0);
		Consulta unaConsulta = new Consulta(RepositorioPOI.getInstance());
		usr.agregarConsulta(unaConsulta,LocalDate.now());
		List<POI> poisTotales = RepositorioPOI.getInstance().pois;
		List<POI> poisDisponibles = 
				RepositorioPOI.getInstance().pois.stream().filter(poi-> usr.estaDisponible(poi))
				.collect(Collectors.toList());
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("poisTotales", poisTotales);
		viewModel.put("poisDisponibles", poisDisponibles);
		viewModel.put("cantidadDisponibles", poisDisponibles.size());
		viewModel.put("cantidadTotales", poisTotales.size());
		return new ModelAndView(viewModel, "consultaDisponibilidad.html");
	}

}
