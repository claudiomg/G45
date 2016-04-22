package poi;

import calculo.Calculo;
import usuario.Posicion;

public class LocalComercial extends POI{

	private TipoDeLocalComercial tipo;
	
	public LocalComercial(TipoDeLocalComercial tipoDeLocal){
		this.tipo = tipoDeLocal;
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < this.tipo.getRadioDeCercania();
	}
	
}
