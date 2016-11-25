package poi.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import poi.modelo.usuario.Terminal;
import poi.utilidades.Consulta;

public class RepositorioConsultas {
	
	protected static RepositorioConsultas instance;
	private static Datastore datastore;
	private static Morphia morphia;
	private static MongoClient cliente;
	protected RepositorioConsultas() { /*Existe para anular la instanciacion*/ };
	public static RepositorioConsultas getInstance() {
		if(instance == null) {
			instance = new RepositorioConsultas();
			cliente = new MongoClient();
			morphia = new Morphia();
			morphia.mapPackage("poi.repositorios");
			datastore = morphia.createDatastore(cliente, "Grupo45_POIs");
		}
		return instance;
	}
	public List<Consulta> registros = new ArrayList <Consulta>(); 

	public void agregarRegistro(Consulta unRegistro){
		datastore.save(unRegistro);
	}
	public void eliminarRegistro(Consulta unRegistro){
		datastore.delete(unRegistro);
	}
	public void cleanRepository() {
		this.getRegistros().stream().forEach(consulta -> this.quitarRegistroDeLaBase(consulta));
	}
	public List<Consulta> getRegistros() {
		return datastore.createQuery(Consulta.class).asList();
	}
	public Consulta getRegistroPorId(ObjectId id){
		return datastore.get(Consulta.class, id);
	}
	public void quitarRegistroDeLaBase(Consulta unRegistro){
		Query<Consulta> query = datastore.createQuery(Consulta.class).filter("_id", unRegistro.getId());
		datastore.delete(query);
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
	
    public List<Consulta> busquedaHistorialConsultas(LocalDate fechaInicio,LocalDate fechaFinal,Terminal usu){
		List<Consulta> consultas = this.filtraConsultaPorTerminal(this.getRegistros(), usu);
		return consultas
				.stream()
				.filter(consulta -> ( consulta.getFecha().isAfter(fechaInicio) && consulta.getFecha().isBefore(fechaFinal)))
				.collect(Collectors.toList());
	}	
}
