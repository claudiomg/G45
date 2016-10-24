package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

import poi.utilidades.Calculo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

@Entity
@Table(name = "CGPs")
@PrimaryKeyJoinColumn(name = "PoiId")
public class CGP extends POI{

	@OneToMany(cascade = CascadeType.PERSIST
			, fetch = FetchType.EAGER)
	@JoinColumn(name="CgpId")
	private List<Posicion> verticesComuna = new ArrayList<Posicion>();
	@OneToMany(cascade = CascadeType.PERSIST
			, fetch = FetchType.EAGER)
	@JoinColumn(name="CgpId")
	private List<ServicioDeCGP> servicios = new ArrayList<ServicioDeCGP>();
	
	public CGP(String nombre,Posicion posicion,Direccion direccion, List<Posicion> verticesComuna) {
		this.setNombre(nombre);
		this.setPosicion(posicion);
		this.setDireccion(direccion);
		this.setVerticesComuna(verticesComuna);
		this.inicializarPalabrasClave();
	}

	@Override
	public void inicializarPalabrasClave() {
		this.addPalabraClave("centro");
		this.addPalabraClave("comuna");
		this.addPalabraClave("asistencia");
		this.addPalabraClave("gestion");
	}

	public boolean estaCercaDe(Posicion posicionUsuario){
		boolean estaCerca = Calculo.coordenadasEnComuna(this.getVerticesComuna(), posicionUsuario); 
		return estaCerca;
	}
	
	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		return (this.getServicios().stream().anyMatch(
	            unServicio -> unServicio.estaDisponible(unaFechaHora)));
	}
	public void agregarServicio(ServicioDeCGP unServicio) {
		this.getServicios().add(unServicio);
	}
	
	public void quitarServicio(ServicioDeCGP unServicio){
		this.getServicios().remove(unServicio);
	}

	public List<ServicioDeCGP> getServicios() {
		return this.servicios;
	}
	
	public void quitarVertice(Posicion posicion){
		this.getVerticesComuna().remove(posicion);
	}
	
	public void agregarVertice(Posicion posicion){
		this.getVerticesComuna().add(posicion);
	}

	public List<Posicion> getVerticesComuna() {
		return this.verticesComuna;
	}

	public void setVerticesComuna(List<Posicion> verticesComuna) {
		this.verticesComuna = verticesComuna;
	}

	public void setServicios(List<ServicioDeCGP> servicios) {
		this.servicios = servicios;
	}

	@Override
	public void completeViewData(HashMap<String, Object> element) {
		element.put("icon", "icons/cgp32.png");
		element.put("titulo", "CGP");
		element.put("latitud", this.getPosicion().getLatitud());
		element.put("longitud", this.getPosicion().getLongitud());
		element.put("direccion", this.getDireccion().getCalle() + " " + this.getDireccion().getNumero());
		element.put("zona", this.getDireccion().getBarrio());
		List<HashMap<String, Object>> servicios = new ArrayList<HashMap<String,Object>>();
		for ( ServicioDeCGP servicio : this.getServicios()){
			HashMap<String,Object> elementByService = new HashMap<String,Object>();
			servicio.completeViewData(elementByService);
			servicios.add(element);
		}
		element.put("servicios", servicios);
	}


	
}
