package poi.modelo.puntoDeInteres;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Calculo;
import poi.utilidades.Posicion;

public abstract class POI {
	public List<String> etiquetas;
	public Posicion posicion;
	public ArrayList<DisponibilidadHoraria> horariosDisponibles = new ArrayList<DisponibilidadHoraria>();

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
	public boolean estaDisponible(LocalTime unaHora, DayOfWeek unDia) {
		return this.horariosDisponibles.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaHora, unDia));
	}
	
	public void setDisponibilidadHoraria(DisponibilidadHoraria diaYHora ){
		this.horariosDisponibles.add(diaYHora);
		
	}
	
	public List<DisponibilidadHoraria> getDisponibilidadHoraria(){
		return this.horariosDisponibles;
		
	}
		
	public void modificarAtributos (Posicion posicion, String etiqueta, String etiqueta2){
		this.setPosicion(posicion);
		this.agregarEtiqueta(etiqueta);
		this.eliminarEtiqueta(etiqueta2);
	}
	
}
