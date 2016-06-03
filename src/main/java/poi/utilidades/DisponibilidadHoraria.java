package poi.utilidades;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import poi.utilidades.Feriados;

public class DisponibilidadHoraria {
	
	private ArrayList<TimeRange> rangoHorario= new ArrayList<TimeRange>() ;
	private DayOfWeek dia;
	
	
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
	
	public boolean noEsUnFeriado(Feriados feriados, LocalDateTime diaActual){
		return feriados.getFeriados().stream().noneMatch(unDia -> unDia.getDayOfMonth()== diaActual.getDayOfMonth())
				|| feriados.getFeriados().stream().noneMatch(unDia -> unDia.getMonth() == diaActual.getMonth());
	}
	
	public boolean estaDisponible(LocalDateTime unaFechaHora, Feriados feriados){
		DayOfWeek unDia = unaFechaHora.getDayOfWeek();
		LocalTime unaHora = unaFechaHora.toLocalTime();
		return  this.diaDisponible(unDia)&& this.rangoDisponible(unaHora)&& this.noEsUnFeriado(feriados, unaFechaHora);
	}
	
	
	
	
	

}
