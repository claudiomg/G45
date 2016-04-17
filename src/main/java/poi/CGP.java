package poi;

import java.util.ArrayList;
import java.util.List;

import calculo.Calculo;
import usuario.Posicion;

public class CGP implements POI{
	private Posicion posicion;
	private List<Posicion> verticesComuna = new ArrayList<Posicion>();
	
	public CGP(List<Posicion> verticesComuna) {
		this.verticesComuna = verticesComuna;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		boolean estaCerca = Calculo.coordenadasEnComuna(verticesComuna, posicionUsuario); 
		return estaCerca;
	};
}
