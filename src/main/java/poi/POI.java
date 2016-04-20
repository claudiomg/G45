package poi;

import java.time.LocalDateTime;
import java.util.List;

import calculo.Calculo;
import usuario.Posicion;

public abstract class POI {
	public List<String> etiquetas;
	public Posicion posicion;

	public POI() {
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < 0.5;
	}
	
	public List<String> getEtiquetas(){
		return this.etiquetas;
	}
	
	public void agregarEtiqueta(String etiqueta){
		this.etiquetas.add(etiqueta);
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public boolean estaDisponible(LocalDateTime unaHora) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
