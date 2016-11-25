package poi.modelo.puntoDeInteres;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

import poi.utilidades.Direccion;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

@Entity
@Table(name = "SucursalesBancos")
@PrimaryKeyJoinColumn(name = "PoiId")
public class SucursalBanco extends POI{
	
	private String sucursal = new String();
	private String gerente = new String();
	@ElementCollection
	@CollectionTable(
	        name="servicios",
	        joinColumns=@JoinColumn(name="PoiId")
	  )
	private List<String> servicios = new ArrayList<String>();
	
	public  SucursalBanco() {
		
	}
	
	public SucursalBanco(String nombre, Posicion posicion, Direccion direccion) {
		this.setNombre(nombre);
		this.setPosicion(posicion);
		this.setDireccion(direccion);
		this.inicializarPalabrasClave();
		this.setDefaultDisponibility();
	}
	
	private void setDefaultDisponibility() {
		DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
		DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
		DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
		DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
		DisponibilidadHoraria viernes = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
		
		TimeRange rango = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(15,00,0));
		
		lunes.agregarNuevoRango(rango);
		martes.agregarNuevoRango(rango);
		miercoles.agregarNuevoRango(rango);
		jueves.agregarNuevoRango(rango);
		viernes.agregarNuevoRango(rango);
		
		this.addDisponibilidadDeAtencion(lunes);
		this.addDisponibilidadDeAtencion(martes);
		this.addDisponibilidadDeAtencion(miercoles);
		this.addDisponibilidadDeAtencion(jueves);
		this.addDisponibilidadDeAtencion(viernes);
	}
	
	
	public List<String> getEtiquetas(){
		List<String> lista = super.getEtiquetas();
		lista.addAll(this.getServicios());
		lista.add(this.getSucursal());
		lista.add(this.getGerente());
		return lista;
	}

	@Override
	public void inicializarPalabrasClave() {
		this.addPalabraClave("banco");
		this.addPalabraClave("sucursal");
	}
	
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	public List<String> getServicios() {
		return servicios;
	}
	
	public void agregarServicio(String servicio) {
		this.getServicios().add(servicio);
	}

	@Override
	public void completeViewData(HashMap<String, Object> element) {
		element.put("icon", "map-icon-bank");
		element.put("cssClass", "data-banco");
		element.put("titulo", "Sucursal de Banco");
		element.put("latitud", this.getPosicion().getLatitud());
		element.put("longitud", this.getPosicion().getLongitud());
		element.put("direccion", this.getDireccion().getCalle() + " " + this.getDireccion().getNumero());
		element.put("zona", this.getDireccion().getBarrio());
		element.put("servicios", this.getServicios());
	}

}
