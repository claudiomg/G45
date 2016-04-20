package poi;

import java.util.ArrayList;
import java.util.List;

import calculo.Calculo;
import usuario.Posicion;

public class CGP extends POI{
	private List<Posicion> verticesComuna = new ArrayList<Posicion>();
	
	public CGP(List<String> etiquetas,Posicion posicion,List<Posicion> verticesComuna) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
		this.verticesComuna = verticesComuna;
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		boolean estaCerca = Calculo.coordenadasEnComuna(verticesComuna, posicionUsuario); 
		return estaCerca;
	};
}
