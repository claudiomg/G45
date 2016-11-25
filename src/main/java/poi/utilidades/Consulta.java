package poi.utilidades;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
*/


import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioConsultas;
import poi.modelo.puntoDeInteres.POI;

@Entity("Consultas")
public class Consulta implements WithGlobalEntityManager {
	@Id
	private  ObjectId consultaId;
	private LocalDateTime comienzoProceso;
	private LocalDateTime finProceso;
	private Duration duracionProceso;
	private String palabraBuscada;
	
	@Embedded
	private Terminal user;
	
	private int cantidadEncontrada;
	
	@Embedded
	private List<POI> poisEncontrados;
	
	public Consulta(){};
	
	public Consulta(Terminal user, String palabraBuscada) {
		this.setUser(user);
		this.setPalabraBuscada(palabraBuscada);
	}
	public ObjectId getId (){
		return consultaId;
	}
	public void calcularTiempoProceso() {
		Duration duration = Duration.between(this.getComienzoProceso(), this.getFinProceso());
		Duration maximoPermitido = PoiSystemConfiguration.getInstance().getTiempoProcesamientoMaximo();
		this.setDuracionProceso(duration);
		if (duration.toMillis() > maximoPermitido.toMillis() ){
			Notificador.informarProcesamientoExcesivo(this);
		}
	}
	
	public LocalDate getFecha(){
		//devuelvo la fecha de inicio
		return this.getComienzoProceso().toLocalDate();
	}

	public String getPalabraBuscada() {
		return palabraBuscada;
	}

	public Terminal getUser() {
		return user;
	}

	public void setComienzoProceso(LocalDateTime comienzoProceso) {
		this.comienzoProceso = comienzoProceso;
	}

	public void setFinProceso(LocalDateTime finProceso) {
		this.finProceso = finProceso;
	}

	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}

	public LocalDateTime getComienzoProceso() {
		return comienzoProceso;
	}
	public LocalDateTime getFinProceso() {
		return finProceso;
	}
	public void setUser(Terminal user) {
		this.user = user;
	}
	public Duration getDuracionProceso() {
		return duracionProceso;
	}
	public void setDuracionProceso(Duration duracionProceso) {
		this.duracionProceso = duracionProceso;
	}
	
	public int getCantidadEncontrada(){
			
		return cantidadEncontrada;
	}
	
	public void setCantidadEncontrada(int cantidad) {
		
		cantidadEncontrada = cantidad;
	}
	
	public void setPoisEncontrados (List<POI> pois){
		poisEncontrados = new ArrayList<>();
		poisEncontrados.addAll(pois);
	}
	
	public List<POI> getPois(){
		return poisEncontrados;
	}
	

	public void persistir(Consulta consulta){
		entityManager().getTransaction().begin();
		entityManager().persist(consulta);
		entityManager().getTransaction().commit();
	}

	
}
