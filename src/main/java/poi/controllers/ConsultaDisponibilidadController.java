package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultaDisponibilidadController {

	public ModelAndView listar(Request request, Response response) {
		Terminal usr = new Terminal();		
		Posicion posicion1 = new Posicion(40.417, -3.703);
		usr.setPosicion(posicion1);
		Consulta unaConsulta = new Consulta(RepositorioPOI.getInstance());
		usr.agregarConsulta(unaConsulta,LocalDate.now());
		List<POI> pois = RepositorioPOI.getInstance().pois;
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("pois", pois);
		viewModel.put("cantidadPois", pois.size());
		return new ModelAndView(viewModel, "consultaDisponibilidad.html");
	}

}
