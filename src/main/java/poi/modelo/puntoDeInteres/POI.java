package poi.modelo.puntoDeInteres;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalTime;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Calculo;
import poi.utilidades.Posicion;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.ExcepcionHorarioCambiado;

public abstract class POI {
	public List<String> etiquetas;
	public Posicion posicion;
	public ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion = new ArrayList<DisponibilidadHoraria>();
	public ExcepcionSinAtencion feriados;
	public ArrayList <ExcepcionHorarioCambiado> horariosCambiados = new ArrayList<ExcepcionHorarioCambiado>();

	public POI() {
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		//TODO
		double distancia = Calculo.distanciaLineal(this.posicion, posicionUsuario);
		double distanciaLineal = Calculo.distanciaLineal(this.posicion, posicionUsuario);
		return distancia < 0.5 && distanciaLineal < 0.5;
	} 
	
	public List<String> getEtiquetas(){
		return this.etiquetas;
	}
	
	public void agregarEtiqueta(String etiqueta){
		this.etiquetas.add(etiqueta);
	}
	
	public void eliminarEtiqueta (String etiqueta){
		this.etiquetas.remove(etiqueta);
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		if (this.horariosCambiados.stream().anyMatch(unHorario -> unHorario.diaDisponible(unaFechaHora))){
			return this.horariosCambiados.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora));
		}
		else
		return this.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora, this.feriados))
				 && this.feriados.noEsUnFeriado(unaFechaHora);
	}
	
	public void addDisponibilidadDeAtencion(DisponibilidadHoraria diaYHora ){
		this.disponibilidadesDeAtencion.add(diaYHora);
		
	}
	
	public List<DisponibilidadHoraria> getDisponibilidadHoraria(){
		return this.disponibilidadesDeAtencion;
		
	}

	public ArrayList<DisponibilidadHoraria> getDisponibilidadesDeAtencion() {
		return disponibilidadesDeAtencion;
	}

	public void setDisponibilidadesDeAtencion(ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion) {
		this.disponibilidadesDeAtencion = disponibilidadesDeAtencion;
	}
	
	public void setFeriados(ExcepcionSinAtencion feriados){
		this.feriados = feriados;
	}
	
	public void agregarFeriados(LocalDateTime unFeriado){
		this.feriados.agregarFeriados(unFeriado);
	
	}
		
	public void modificarAtributos (Posicion posicion, String etiqueta, String etiqueta2){
		this.setPosicion(posicion);
		this.agregarEtiqueta(etiqueta);
		this.eliminarEtiqueta(etiqueta2);
	}
	
}
