package poi.servicioRest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;

public class ServicioRest {
	protected static ServicioRest instance;
	protected ServicioRest() { /*Existe para anular la instanciacion*/ };
	
	public static ServicioRest getInstance() {
		if(instance == null) {
			instance = new ServicioRest();
		}
		return instance;
	}

	public List<HashMap<String, Object>> restBusquedaUsuario(String terminal) {
		List<Consulta> consultas = RepositorioConsultas.getInstance().getRegistros();
		List<HashMap<String, Object>> listado = 
				this.convertConsultasPorTerminal(consultas, terminal);
		return listado;
	}	

	public List<HashMap<String, Object>> restBusquedaFechas(String fechaInicio, String fechaFin) {
		List<Consulta> consultas = RepositorioConsultas.getInstance().getRegistros();
		List<HashMap<String, Object>> listado = 
				this.convertConsultasPorFecha(consultas, fechaInicio, fechaFin);
		return listado;
	}	
	
	public List<HashMap<String, Object>> convertConsultasPorTerminal(List<Consulta> consultas, String usuario ){
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
