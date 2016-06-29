package poi.utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.repositorios.RepositorioAbstracto;

public class Consulta {
	private RepositorioAbstracto repositorio2;
	private RepositorioAbstracto repositorio;
	private List<POI> poisEncontrados = new ArrayList<POI>();
	private BusquedaDeBancos busquedaDeBancos;
	private List<POI> poisEncontradosEnExterno= new ArrayList<POI>();
	//Expresados en milisegundos
	private double comienzoProceso;
	private double finProceso;
	private double tiempoProceso;
	private double tiempoProcesamientoMaximo = 10.0;
	private HistorialConsulta historial;

	public Consulta(RepositorioAbstracto repositorio3) {
		this.repositorio = repositorio3;
	}
	
	public void setRepoExterno(RepositorioAbstracto repo2){
		this.repositorio2=repo2;
	}
	
	public List<POI> getPoisEncontrados() {
		return poisEncontrados;
	}

	public boolean sonCercanos(Posicion posicion, POI poi) {
		comienzoProceso = System.currentTimeMillis()/1000;
		boolean cercanos = poi.estaCercaDe(posicion);
		finProceso = System.currentTimeMillis()/1000;
		calcularTiempoProceso(comienzoProceso, finProceso);
		return cercanos;
	}

	public List<POI> buscarPorPalabra(String palabra) {
		comienzoProceso = System.currentTimeMillis()/1000;
		Stream<POI> listaFiltrada = filtrarPorParlabra(palabra);				
		List<POI> poisEncontrados = asignarAPoisEncontrados(listaFiltrada);
		finProceso = System.currentTimeMillis()/1000;
		calcularTiempoProceso(comienzoProceso, finProceso);		
		return poisEncontrados;
	}
	
	public List<POI> buscarPorPalabraEnExterno(String palabra){
		Stream<POI> listaFiltrada2 = filtrarPorParlabraExternos(palabra);	
	    return asignarAPoisEncontrados2(listaFiltrada2);
		
	}
	
	public Stream<POI> filtrarPorParlabra(String palabra){
		return repositorio.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
	}
	
	public Stream<POI> filtrarPorParlabraExternos(String palabra){
		return repositorio2.getPois().stream().filter(unPOI -> unPOI.getEtiquetas().contains(palabra));
	}
	
	public List<POI> asignarAPoisEncontrados2(Stream<POI> lista){
		this.setPoisEncontradosEnExterno(lista.collect(Collectors.toCollection(ArrayList::new)));
		return this.getPoisEncontradosEnExterno();
	} 
	
	public List<POI> asignarAPoisEncontrados(Stream<POI> lista){
		this.setPoisEncontradosEnExterno(lista.collect(Collectors.toCollection(ArrayList::new)));
		return this.getPoisEncontrados();
	}

	public List<POI> getPoisEncontradosEnExterno() {
		return poisEncontradosEnExterno;
	}

	public void setPoisEncontradosEnExterno(List<POI> poisEncontrados) {
		this.poisEncontrados = poisEncontrados;
		
	}
	
	public void setPoisEncontrados(List<POI> poisEncontradosEnExterno) {
		this.poisEncontradosEnExterno = poisEncontradosEnExterno;
		
	}

	public List<SucursalBanco> buscarBancosPorNombreYServicio(String nombreBanco, String servicio) {
		return busquedaDeBancos.busquedaDeBancos(nombreBanco, servicio);
	}

	public boolean estaDisponible(POI poi) {
		comienzoProceso = System.currentTimeMillis()/1000;
		boolean disponibilidad = poi.estaDisponible(LocalDateTime.now());
		finProceso = System.currentTimeMillis()/1000;
		calcularTiempoProceso(comienzoProceso, finProceso);
		return disponibilidad;
	}

	private void calcularTiempoProceso(double comienzo, double fin) {
		this.tiempoProceso = fin - comienzo;
		this.historial.setTiempoProceso(this.tiempoProceso);
		if (this.tiempoProceso > this.tiempoProcesamientoMaximo){
			Notificador.informarProcesamientoExcesivo(this.tiempoProceso - this.tiempoProcesamientoMaximo);
		}
	}
	
	public double getTiempoProcesamientoMaximo() {
		return tiempoProcesamientoMaximo;
	}

	public void setTiempoProcesamientoMaximo(double tiempoProcesamientoMaximo) {
		this.tiempoProcesamientoMaximo = tiempoProcesamientoMaximo;
	}

	public BusquedaDeBancos getBusquedaDeBancos() {
		return busquedaDeBancos;
	}

	public void setBusquedaDeBancos(BusquedaDeBancos busquedaDeBancos) {
		this.busquedaDeBancos = busquedaDeBancos;
	}
	public double getComienzoProceso() {
		return comienzoProceso;
	}

	public double getFinProceso() {
		return finProceso;
	}

	public double getTiempoProceso() {
		return tiempoProceso;
	}
	public void setHistorial(LocalDate fecha, String nombre) {
		this.historial = new HistorialConsulta(fecha, nombre);
		this.historial.agregarARepositorio();
		
	}	

	public void generarHistorial(String frase) {
		this.historial.setFraseBuscada(frase);
		
		
		
	}

}
