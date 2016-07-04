package poi.modelo.puntoDeInteres;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

import poi.acciones.Accion;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Calculo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.ExcepcionHorarioCambiado;

public abstract class POI {
	private String nombre = new String();
	private Posicion posicion;
	private Direccion direccion;
	private List<String> palabrasClave = new ArrayList<String>();
	private ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion = new ArrayList<DisponibilidadHoraria>();
	private ExcepcionSinAtencion feriados;
	private ArrayList <ExcepcionHorarioCambiado> horariosCambiados = new ArrayList<ExcepcionHorarioCambiado>();
	private List<Accion> acciones = new ArrayList<Accion>();
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaLineal(this.posicion, posicionUsuario);
		double distanciaLineal = Calculo.distanciaLineal(this.posicion, posicionUsuario);
		return distancia < 0.5 && distanciaLineal < 0.5;
	} 
	
	public boolean matches(String palabraBuscada) {
		//este metodo se encarga de buscar coincidencias de la palabra buscada y yo mismo
		String palabra = palabraBuscada.toLowerCase();
		return 
			(this.getNombre().toLowerCase().contains(palabra)) ||
			(this.getEtiquetas().stream().anyMatch(tag -> tag.toLowerCase().contains(palabra)));
	}
	
	public List<String> getEtiquetas(){
		List<String> lista = new ArrayList<String>();
		lista.add(this.getNombre());
		lista.addAll(this.getDireccion().getEtiquetas());
		lista.addAll(this.getPalabrasClave());
		return lista;
	}
	//Ahora es una clase concreta del Command Acciones
	//public boolean estaDisponible(LocalDateTime unaFechaHora) {
	//	if (this.horariosCambiados.stream().anyMatch(unHorario -> unHorario.diaDisponible(unaFechaHora))){
	//	return this.horariosCambiados.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora));
	//	}
	//	else
	//		return this.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora, this.feriados))
	//				&& this.feriados.noEsUnFeriado(unaFechaHora);
	//}

	public abstract void inicializarPalabrasClave();
	
	protected void addPalabraClave(String palabra) {
		this.getPalabrasClave().add(palabra);
	}
	
	public void addDisponibilidadDeAtencion(DisponibilidadHoraria diaYHora ){
		this.disponibilidadesDeAtencion.add(diaYHora);
		
	}
	
	public List<DisponibilidadHoraria> getDisponibilidadHoraria(){
		return this.disponibilidadesDeAtencion;
		
	}

	public ArrayList<DisponibilidadHoraria> getDisponibilidadesDeAtencion() {
		return disponibilidadesDeAtencion;
	}

	public void setDisponibilidadesDeAtencion(ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion) {
		this.disponibilidadesDeAtencion = disponibilidadesDeAtencion;
	}
	
	public void setFeriados(ExcepcionSinAtencion feriados){
		this.feriados = feriados;
	}
	
	
	public void agregarFeriados(LocalDateTime unFeriado){
		this.feriados.agregarFeriados(unFeriado);
	
	}
	
	public void agregarHorarioCambiado(ExcepcionHorarioCambiado fechaHora){
		this.horariosCambiados.add(fechaHora);
	}
		
	public void modificarAtributos (Posicion posicion, Direccion direccion){
		this.setPosicion(posicion);
		this.setDireccion(direccion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void agregarAccion(Accion accion){
		this.acciones.add(accion);
	}

	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		//este metodo debe ser modificado segun cada subclase
		return true;
	}
	
	public ArrayList<ExcepcionHorarioCambiado> getHorariosCambiados() {
		return horariosCambiados;
	}

	public void setHorariosCambiados(ArrayList<ExcepcionHorarioCambiado> horariosCambiados) {
		this.horariosCambiados = horariosCambiados;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public ExcepcionSinAtencion getFeriados() {
		return feriados;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public List<String> getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabrasClave(List<String> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
}
