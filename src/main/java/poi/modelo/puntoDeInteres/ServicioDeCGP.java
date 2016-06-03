package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import poi.utilidades.DisponibilidadHoraria;


public class ServicioDeCGP {

	private ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion = new ArrayList<DisponibilidadHoraria>();
	private String nombre;
	
	public List<DisponibilidadHoraria> getDisponibilidadHoraria(){
		return this.disponibilidadesDeAtencion;
		
	}
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	public String getNombre(){
		return this.nombre;
	}

	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		return this.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora));
	}

	public void agregarDisponibilidadDeAtencion(DisponibilidadHoraria disponibilidadHoraria) {
		this.disponibilidadesDeAtencion.add(disponibilidadHoraria);
	}
	
}
