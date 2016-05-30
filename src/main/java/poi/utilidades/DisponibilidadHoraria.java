package poi.utilidades;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class DisponibilidadHoraria {
	
	private ArrayList<TimeRange> rangoHorario= new ArrayList<TimeRange>() ;
	private DayOfWeek dia;
	
	public DisponibilidadHoraria(DayOfWeek unDia){
		this.dia = unDia; 
		
	}
	
	public void agregarNuevoRango (TimeRange otroRango) {
		this.rangoHorario.add(otroRango);
		
	}
	
	public boolean rangoDisponible (LocalTime unaHora){
		return this.rangoHorario.stream().anyMatch(unRango -> unRango.isValidValue(unaHora));
	}
	
	public boolean diaDisponible (DayOfWeek unDia){
		return this.dia.equals(unDia);
	}
	
	public boolean estaDisponible(LocalTime unaHora, DayOfWeek unDia){
		return  this.diaDisponible(unDia)&& this.rangoDisponible(unaHora);
	}
	
	
	
	
	

}
