package poi;

import java.util.List;

import calculo.Calculo;
import usuario.Posicion;

public class ParadaColectivo extends POI{

	public ParadaColectivo(List<String> etiquetas,Posicion posicion) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
	}

	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < 0.1;
	};

}
