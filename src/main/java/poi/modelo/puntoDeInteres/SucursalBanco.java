package poi.modelo.puntoDeInteres;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class SucursalBanco extends POI{
	
	private String nombre;
	private String sucursal;
	private String gerente;
	private List<String> servicios;
	

	public SucursalBanco(List<String> etiquetas,Posicion posicion) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
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
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}
	public boolean haceEsteServicio(String servicio){
		
		return this.getServicios().contains(servicio);
		
	}
}
