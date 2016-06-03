package poi.modelo.puntoDeInteres;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Calculo;
import poi.utilidades.Posicion;

public abstract class POI {
	public List<String> etiquetas;
	public Posicion posicion;
	public ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion = new ArrayList<DisponibilidadHoraria>();

	public POI() {
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
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
		return this.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora));
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
		
	public void modificarAtributos (Posicion posicion, String etiqueta, String etiqueta2){
		this.setPosicion(posicion);
		this.agregarEtiqueta(etiqueta);
		this.eliminarEtiqueta(etiqueta2);
	}
	
}
