package poi.modelo.usuario;

import poi.utilidades.Posicion;
import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioPOI;

public class Administrador {
	
	private RepositorioPOI repositorio;
	
	public Administrador(RepositorioPOI repositorio){
		this.repositorio = repositorio;
	}
		public void agregarPOI(POI poi){
			this.repositorio.agregarPOI(poi);			
	}	
		public void removerPOI (POI poi){
			this.repositorio.removerPOI(poi);
	}
		public void modificarPOI(POI poi, Posicion posicion, String etiqueta, String etiqueta2){
			this.repositorio.modificarPOI(poi, posicion, etiqueta, etiqueta2);
	}
		
			
}
		



