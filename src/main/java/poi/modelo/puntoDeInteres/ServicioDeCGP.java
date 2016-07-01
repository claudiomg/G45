package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.ExcepcionHorarioCambiado;
import poi.utilidades.ExcepcionSinAtencion;


public class ServicioDeCGP {

	private ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion = new ArrayList<DisponibilidadHoraria>();
    private ExcepcionSinAtencion feriados;
    private ArrayList<ExcepcionHorarioCambiado> horariosCammbiados = new ArrayList<ExcepcionHorarioCambiado>();
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
	
	public void setFeriados (ExcepcionSinAtencion feriados){
		this.feriados= feriados;
		
	}

	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		return this.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora, this.feriados));
	}

	public void agregarDisponibilidadDeAtencion(DisponibilidadHoraria disponibilidadHoraria) {
		this.disponibilidadesDeAtencion.add(disponibilidadHoraria);
	}
	
}
