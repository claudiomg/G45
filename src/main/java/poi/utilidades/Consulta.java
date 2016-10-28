package poi.utilidades;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioConsultas;
import poi.modelo.puntoDeInteres.POI;

@Entity
@Table(name="Consultas")
public class Consulta implements WithGlobalEntityManager {
	@Id
	@GeneratedValue
	@Column(name="consultaId")
	private Long consultaId;
	@Column(name = "comienzoProceso")
	private LocalDateTime comienzoProceso;
	@Column(name = "finProceso")
	private LocalDateTime finProceso;
	@Column(name = "duracionProceso")
	private Duration duracionProceso;
	@Column(name="palabraBuscada")
	private String palabraBuscada;
	@ManyToOne
	@JoinColumn(name="terminalId")
	private Terminal user;
	
	private int cantidadEncontrada;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="poisEncontrados", 
	joinColumns=@JoinColumn(name="consultaId"), inverseJoinColumns=@JoinColumn(name="PoiId"))  
	private List<POI> poisEncontrados;
	
	public Consulta(){};
	
	public Consulta(Terminal user, String palabraBuscada) {
		this.setUser(user);
		this.setPalabraBuscada(palabraBuscada);
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
	

	public void persistir(Consulta consulta){
		entityManager().getTransaction().begin();
		entityManager().persist(consulta);
		entityManager().getTransaction().commit();
	}

	
}
