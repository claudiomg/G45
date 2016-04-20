package poi;

import java.time.LocalDateTime;
import java.util.List;

import usuario.Posicion;

public abstract class POI {
	public List<String> etiquetas;
	public Posicion posicion;

	public POI() {
	}
	public boolean estaCercaDe(Posicion posicion){
		return true;
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
