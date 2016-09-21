package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.List;

import poi.utilidades.Calculo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

import javax.persistence.*;

@Entity
@Table(name = "ParadasColectivos")
public class ParadaColectivo extends POI{
	@Id
	@GeneratedValue
	@Column(name = "ParadaColectivoId")
	private Long ParadaId;
	
	private String linea = new String();

	public ParadaColectivo(String linea,Posicion posicion, Direccion direccion) {
		this.setLinea(linea);
		this.setPosicion(posicion);
		this.setDireccion(direccion);
		this.inicializarPalabrasClave();
	}
	
	@Override
	public void inicializarPalabrasClave() {
		this.addPalabraClave("colectivo");
		this.addPalabraClave("bondi");
		this.addPalabraClave("parada");
	}
	
	public List<String> getEtiquetas(){
		List<String> lista = super.getEtiquetas();
		lista.add(this.getLinea());
		return lista;
	}

	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaLineal(this.getPosicion(), posicionUsuario);
		return distancia < 0.1;
	};
	
	public boolean estaDisponible(LocalDateTime unaHora) {
		return true;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	@Override
	public String getNombre() {
		return this.getLinea();
	}

}
