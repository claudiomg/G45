package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.List;

import poi.utilidades.Calculo;
import poi.utilidades.Posicion;

public class ParadaColectivo extends POI{

	public ParadaColectivo(List<String> etiquetas,Posicion posicion) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
	}

	public boolean estaCercaDe(Posicion posicionUsuario){
		//TODO
		double distancia = Calculo.distanciaLineal(this.posicion, posicionUsuario);
		return distancia < 0.1;
	};
	
	public boolean estaDisponible(LocalDateTime unaHora) {
		// TODO Auto-generated method stub
		return true;
	}

}
