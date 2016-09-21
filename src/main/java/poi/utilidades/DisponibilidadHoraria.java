package poi.utilidades;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="DisponibilidadesHorarias")
public class DisponibilidadHoraria {
	
	@Id
	@GeneratedValue
	@Column(name="disponibilidadId")
	private Long disponibilidadId;
	@OneToMany(cascade = CascadeType.PERSIST
			, fetch = FetchType.EAGER)
	@JoinColumn(name="disponibilidadId")
	private ArrayList<TimeRange> rangoHorario= new ArrayList<TimeRange>() ;
	@Convert(converter= DayOfWeek.class)
	@Column(name="dia")
	private DayOfWeek dia;
	
	public DisponibilidadHoraria(){};
	
	public ArrayList<TimeRange> getRangoHorario() {
		return rangoHorario;
	}

	public void setRangoHorario(ArrayList<TimeRange> rangoHorario) {
		this.rangoHorario = rangoHorario;
	}

	public DayOfWeek getDia() {
		return dia;
	}


	public DisponibilidadHoraria(DayOfWeek unDia){
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
	
	
	
	
	

}
