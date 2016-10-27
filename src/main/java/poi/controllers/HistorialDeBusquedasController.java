package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorFecha;
import poi.reportes.ReporteBusquedasPorTerminal;
import poi.repositorios.RepositorioConsultas;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistorialDeBusquedasController {
	
	public ModelAndView render(Request request, Response response){
		
		HashMap<String, Object> viewModel = new HashMap<>();
						
		return new ModelAndView(viewModel, "historialDeBusquedasRealizadas.html");
	}
	
	public ModelAndView mostrarLista(Request request, Response response){
				
		//String fechaInicio = request.queryParams("fechaInicio");
		//String fechaFin = request.queryParams("fechaFin");
		
		String usuario = request.queryParams("usuario");
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		List<Consulta> consultas = RepositorioConsultas.getInstance().getRegistros();
		
		viewModel.put("hasResults", !consultas.isEmpty());
		viewModel.put("result", this.convertConsultas(consultas, usuario));
		
		return new ModelAndView(viewModel, "historialDeBusquedasRealizadas.html");
		
	}
	private List<HashMap<String, Object>> convertConsultas(List<Consulta> consultas, String usuario ) {
		
		List<Consulta> consultasAuxiliar = new ArrayList<>();
		
		consultasAuxiliar.addAll(consultas.stream().filter(c -> c.getUser().getUsuario().toLowerCase().equals(usuario.toLowerCase())).collect(Collectors.toList()));
		
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		for ( Consulta consulta : consultasAuxiliar){
			HashMap<String,Object> element = new HashMap<String,Object>();
			
			element.put("fecha", consulta.getFecha().toString());
			element.put("usuario", consulta.getUser().getUsuario());
			element.put("parametros", consulta.getPalabraBuscada());
			
			array.add(element);
		}
		return array;
}
	


}
