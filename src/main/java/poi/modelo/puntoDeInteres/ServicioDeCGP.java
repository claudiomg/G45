package poi.modelo.puntoDeInteres;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import poi.utilidades.DisponibilidadHoraria;


public class ServicioDeCGP {

	private ArrayList<DisponibilidadHoraria> horariosDisponibles = new ArrayList<DisponibilidadHoraria>();
	private String nombre;

	public void setDisponibilidadHoraria(DisponibilidadHoraria diaYHora ){
		this.horariosDisponibles.add(diaYHora);
		
	}
	
	public List<DisponibilidadHoraria> getDisponibilidadHoraria(){
		return this.horariosDisponibles;
		
	}
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	public String getNombre(){
		return this.nombre;
	}

	public boolean estaDisponible(LocalTime unaHora, DayOfWeek unDia) {
		return this.horariosDisponibles.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaHora, unDia));
	}
	
}
