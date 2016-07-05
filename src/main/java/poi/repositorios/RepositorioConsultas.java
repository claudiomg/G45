package poi.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.modelo.usuario.Terminal;
import poi.utilidades.Consulta;

public class RepositorioConsultas {
	
	protected static RepositorioConsultas instance;
	protected RepositorioConsultas() { /*Existe para anular la instanciacion*/ };
	public static RepositorioConsultas getInstance() {
		if(instance == null) {
			instance = new RepositorioConsultas();
		}
		return instance;
	}
	public List<Consulta> registros = new ArrayList <Consulta>(); 

	public void agregarRegistro(Consulta unRegistro){
		this.registros.add(unRegistro);
	}
	public void eliminarRegistro(Consulta unRegistro){
		this.registros.remove(unRegistro);
	}
	public void cleanRepository() {
		this.registros = new ArrayList <Consulta>();
	}
	public List<Consulta> getRegistros() {
		return this.registros;
	}

	public List<Consulta> filtrarConsultaPorFecha(List<Consulta> lista, LocalDate fecha) {
		return lista
			.stream()
			.filter(consulta -> ( consulta.getFecha().isEqual(fecha)))
			.collect(Collectors.toList());
	}

	public List<Consulta> filtraConsultaPorTerminal(List<Consulta> consultas,Terminal terminal) {
		return consultas
			.stream()
			.filter(consulta-> (consulta.getUser()  == terminal))
			.collect(Collectors.toList());
	}
}
