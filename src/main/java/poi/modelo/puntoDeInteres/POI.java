package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.List;

import poi.utilidades.Calculo;
import poi.utilidades.Posicion;

public abstract class POI {
	public List<String> etiquetas;
	public Posicion posicion;

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
	public boolean estaDisponible(LocalDateTime unaHora) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void modificarAtributos (Posicion posicion, String etiqueta, String etiqueta2){
		this.setPosicion(posicion);
		this.agregarEtiqueta(etiqueta);
		this.eliminarEtiqueta(etiqueta2);
	}
	
}
