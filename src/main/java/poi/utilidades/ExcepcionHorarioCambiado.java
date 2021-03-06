package poi.utilidades;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

@Entity
@Table (name = "ExcepcionesHorarioCambiado")
public class ExcepcionHorarioCambiado  extends AbstractPersistenceTest implements WithGlobalEntityManager{
	@Id
	@GeneratedValue
	@Column (name = "ExcepcionesHorarioCambiadoId")
	private Long ExcepcionesHorarioCambiadoId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="ExcepcionesHorarioCambiadoId")
	private List<TimeRange> rangoCambiado = new ArrayList<TimeRange>();
	
	@Column(name = "diaYMes")
	private LocalDate diaYMes;
	
	public ExcepcionHorarioCambiado(LocalDate unaFecha){
		this.diaYMes = unaFecha;
	}
	
	public void agregarRangoCambiado(TimeRange rango){
		rangoCambiado.add(rango);
			
	}
	
		
	public boolean rangoDisponible (LocalTime unaHora){
		return this.rangoCambiado.stream().anyMatch(unRango -> unRango.isValidValue(unaHora));
	}
	
	public boolean diaDisponible (LocalDateTime unaFechaHora){
		return this.diaYMes.getDayOfMonth() == (unaFechaHora.getDayOfMonth())
				&& this.diaYMes.getMonth().equals(unaFechaHora.getMonth());
	}
    
	public boolean estaDisponible(LocalDateTime unaFechaHora){
		LocalTime hora= unaFechaHora.toLocalTime();
		return this.diaDisponible(unaFechaHora) && this.rangoDisponible(hora);
		
	}
		
	public Long getExcepcionesHorarioCambiadoId() {
		return ExcepcionesHorarioCambiadoId;
	}
	
	public void persistir(ExcepcionHorarioCambiado excepcion){
		entityManager().getTransaction().begin();
		entityManager().persist(excepcion);
		entityManager().getTransaction().commit();
	}

}