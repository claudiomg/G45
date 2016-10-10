package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.puntoDeInteres.ServicioDeCGP;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;

@Entity
@Table(name="DisponibilidadesHorarias")
public class DisponibilidadHoraria implements WithGlobalEntityManager {
	@Id
	@GeneratedValue
	@Column(name="disponibilidadId")
	private Long disponibilidadId;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="disponibilidadId")
	private List<TimeRange> rangoHorario= new ArrayList<TimeRange>() ;
	@Transient
	private DayOfWeek dia;
	@Column(name="day")	
	private String day;
	
		
	public DisponibilidadHoraria(){		
	};
	
	public List<TimeRange> getRangoHorario() {
		return rangoHorario;
	}

	public void setRangoHorario(ArrayList<TimeRange> rangoHorario) {
		this.rangoHorario = rangoHorario;
	}

	public DayOfWeek getDia() {
		return dia;
	}

	public DisponibilidadHoraria(DayOfWeek unDia){
		Locale locale = Locale.getDefault();
		this.day = unDia.getDisplayName(TextStyle.FULL, locale);	
		this.dia = unDia; 
	}
	
	public void agregarNuevoRango(TimeRange otroRango) {
		this.rangoHorario.add(otroRango);		
	}
	
	public boolean rangoDisponible (LocalTime unaHora){
		return this.rangoHorario.stream().anyMatch(unRango -> unRango.isValidValue(unaHora));
	}
	
	public boolean diaDisponible (DayOfWeek unDia){
		return this.dia.equals(unDia);
	}
	
	//public boolean noEsUnFeriado(ExcepcionSinAtencion feriados, LocalDateTime diaActual){
	// feriados.getFeriados().stream().noneMatch(unDia -> unDia.getDayOfMonth()== diaActual.getDayOfMonth())
		//		|| feriados.getFeriados().stream().noneMatch(unDia -> unDia.getMonth() == diaActual.getMonth());
	//}
	
	public boolean estaDisponible(LocalDateTime unaFechaHora, ExcepcionSinAtencion feriados){
		DayOfWeek unDia = unaFechaHora.getDayOfWeek();
		LocalTime unaHora = unaFechaHora.toLocalTime();
		return  this.diaDisponible(unDia)&& this.rangoDisponible(unaHora);//&& this.noEsUnFeriado(feriados, unaFechaHora);
	}

	public void persistir(DisponibilidadHoraria disponibilidad){
		entityManager().getTransaction().begin();
		entityManager().persist(disponibilidad);
		entityManager().getTransaction().commit();
	}	
}
