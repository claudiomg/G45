
package poi.modelo.puntoDeInteres;

import javax.persistence.*;

import poi.utilidades.Calculo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

@Entity
@Table(name = "LocalesComerciales")
@PrimaryKeyJoinColumn(name = "PoiId")
public class LocalComercial extends POI{
	
	@Enumerated(EnumType.STRING)
	private RadioCercania radioDeCercania;
	
	public LocalComercial(String nombre, Posicion posicion, Direccion direccion, RadioCercania radioDeLocal) {
		this.setNombre(nombre);
		this.setPosicion(posicion);
		this.setDireccion(direccion);
		this.setRadioDeCercania(radioDeLocal);
		this.inicializarPalabrasClave();
	}
	@Override
	public void inicializarPalabrasClave() {
		this.addPalabraClave("local");
		this.addPalabraClave("comercial");
	}
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaLineal(this.getPosicion(), posicionUsuario);
		return distancia < radioDeCercania.getValue();
	}

	public RadioCercania getRadioDeCercania() {
		return radioDeCercania;
	}

	public void setRadioDeCercania(RadioCercania radioDeCercania) {
		this.radioDeCercania = radioDeCercania;
	}
}
