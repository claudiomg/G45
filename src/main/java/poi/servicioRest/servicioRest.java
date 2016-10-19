package poi.servicioRest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.java.ws.rest.clases.Usuario;
import com.java.ws.rest.service.Consulta;
import com.java.ws.rest.service.List;
import com.java.ws.rest.service.POI;

import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;
import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.POI;


@Path("/ServicioRestPOIS")
public class servicioRest {
	
	RepositorioConsultas repo = RepositorioConsultas.getInstance();

	@POST
	@Path("/BusquedaPuntoDeInteres")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<POI> busquedaPuntoDeInteres(String criterio){
		
	}

	@POST
	@Path("/HistorialConsultasRealizadas")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
    public List<Consulta> busquedaHistorialConsultas(LocalDate fechaInicio,LocalDate fechaFinal,Terminal usu){
		List<Consulta> consultas = repo.filtraConsultaPorTerminal(repo.getRegistros(), usu);
		return consultas
				.stream()
				.filter(consulta -> ( consulta.getFecha().isAfter(fechaInicio) && consulta.getFecha().isBefore(fechaFinal)))
				.collect(Collectors.toList());
		
		}
}

