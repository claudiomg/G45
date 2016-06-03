package poi.utilidades;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	
	public boolean estaDisponible(LocalDateTime unaFechaHora){
		DayOfWeek unDia = unaFechaHora.getDayOfWeek();
		LocalTime unaHora = unaFechaHora.toLocalTime();
		return  this.diaDisponible(unDia)&& this.rangoDisponible(unaHora);
	}
	
	
	
	
	

}
