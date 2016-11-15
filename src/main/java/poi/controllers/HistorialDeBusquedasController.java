package poi.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import poi.servicioRest.JsonTransformer;
import poi.servicioRest.ServicioRest;
import poi.utilidades.Consulta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.get;

public class HistorialDeBusquedasController {
	
	public ModelAndView render(Request request, Response response){
		
		HashMap<String, Object> viewModel = new HashMap<>();
						
		return new ModelAndView(viewModel, "historialDeBusquedasRealizadas.html");
	}
	
	public ModelAndView mostrarLista(Request request, Response response){

		String fechaInicio = request.queryParams("fechaInicio");
		String fechaFin = request.queryParams("fechaFin");
		String usuario = request.queryParams("usuario");
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		List<Consulta> consultas = RepositorioConsultas.getInstance().getRegistros();
				
		viewModel.put("hasResults", !consultas.isEmpty());
		List<HashMap<String, Object>> listado = this.convertConsultas(consultas, usuario, fechaInicio, fechaFin); 
		viewModel.put("result", listado);
		return new ModelAndView(viewModel, "historialDeBusquedasRealizadas.html");
		
	}
	private List<HashMap<String, Object>> convertConsultas(List<Consulta> consultas, String usuario, String fechaInicio, String fechaFin) {
		
		if (!usuario.equals("")){
			return convertConsultasPorTerminal(consultas, usuario);
		}
		else {
			return convertConsultasPorFecha(consultas, fechaInicio, fechaFin);
			}
		
}
	private List<HashMap<String, Object>> convertConsultasPorTerminal(List<Consulta> consultas, String usuario ){
		List<Consulta> consultasAuxiliar = new ArrayList<>();
		
		consultasAuxiliar.addAll(consultas.stream().filter(c -> c.getUser().getUsuario().toLowerCase().equals(usuario.toLowerCase())).collect(Collectors.toList()));
		
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		for ( Consulta consulta : consultasAuxiliar){
			HashMap<String,Object> element = new HashMap<String,Object>();
			
			element.put("fecha", consulta.getFecha().toString());
			element.put("usuario", consulta.getUser().getUsuario());
			element.put("parametros", consulta.getPalabraBuscada());
			element.put("pois", consulta.getCantidadEncontrada());
			element.put("detallePois","aca va el detalle de los pois");
			
			array.add(element);
		}
		return array;
	}
	
	private List<HashMap<String, Object>> convertConsultasPorFecha(List<Consulta> consultas, String fechaInicio, String fechaFin ){
		
			 LocalDate fechaInicial = LocalDate.of(0, 6, 26);
			 LocalDate fechaFinal = LocalDate.of(2100, 1, 1);
			 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 
			 List<Consulta> consultasAuxiliar = new ArrayList<>();
			
			if (!fechaInicio.equals("") && !fechaFin.equals("")){
				
				
				LocalDate dateStart = LocalDate.parse(fechaInicio, formatter);
				LocalDate dateEnd = LocalDate.parse(fechaFin, formatter);
				
				consultasAuxiliar.addAll(consultas.stream()
						.filter(c -> 
						((c.getFecha().isAfter(dateStart) || c.getFecha().isEqual(dateStart)) 
						&& (c.getFecha().isBefore(dateEnd) || c.getFecha().isEqual(dateEnd))))
						.collect(Collectors.toList()));
				
			} else if (!fechaInicio.equals("")){
				LocalDate dateStart = LocalDate.parse(fechaInicio, formatter);
				
				consultasAuxiliar.addAll(consultas.stream()
						.filter(c -> 
						((c.getFecha().isAfter(dateStart) || c.getFecha().isEqual(dateStart)) 
						&& (c.getFecha().isBefore(fechaFinal) )))
						.collect(Collectors.toList()));
				
			} else if (!fechaFin.equals("")){
				LocalDate dateEnd = LocalDate.parse(fechaFin, formatter);
				
				consultasAuxiliar.addAll(consultas.stream()
						.filter(c -> 
						((c.getFecha().isAfter(fechaInicial)) 
						&& (c.getFecha().isBefore(dateEnd) || c.getFecha().isEqual(dateEnd))))
						.collect(Collectors.toList()));
			} else consultasAuxiliar.addAll(consultas);
			
			
					
			List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
			for ( Consulta consulta : consultasAuxiliar){
				HashMap<String,Object> element = new HashMap<String,Object>();
				
				element.put("fecha", consulta.getFecha().toString());
				element.put("usuario", consulta.getUser().getUsuario());
				element.put("parametros", consulta.getPalabraBuscada());
				element.put("pois", consulta.getCantidadEncontrada());
				
				array.add(element);
			}
			return array;
		}
	

}
