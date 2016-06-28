package poi.modelo.usuario;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;

import java.util.ArrayList;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.repositorios.RepositorioAbstracto;

public class Administrador {
	
	private RepositorioAbstracto repositorio;
	private Rol rol = Rol.ADMINISTRADOR;
	private String mail;

	public Administrador(RepositorioAbstracto repositorio2){
		this.repositorio = repositorio2;
	}
	
	public void agregarPOI(POI poi){
		this.repositorio.agregarPOI(poi);			
	}	
	
	public void removerPOI (POI poi){
		this.repositorio.eliminarPOI(poi);
	}
	
	public void modificarPOI(POI poi, Posicion posicion, String etiqueta, String etiqueta2){
		this.repositorio.modificarPOI(poi, posicion, etiqueta, etiqueta2);
	}
	
	public void agregarEtiquetaAPOI(POI poi, String etiqueta){
		poi.agregarEtiqueta(etiqueta);
	}
	
	public void quitarEtiquetaAPOI(POI poi, String etiqueta){
		poi.eliminarEtiqueta(etiqueta);
	}

	public void cambiarLatitudPOI(POI poi, double latitud){
		poi.posicion.setLatitud(latitud);
	}
	
	public void cambiarLongitudPOI(POI poi, double longitud){
		poi.posicion.setLongitud(longitud);
	}
	
	public void modificarDisponibilidadHorariaAPOI(POI poi, ArrayList<DisponibilidadHoraria> disponibilidades){
		poi.setDisponibilidadesDeAtencion(disponibilidades);
	}
	
	public void agregarServicioACGP(CGP cgp, ServicioDeCGP servicio){
		cgp.agregarServicio(servicio);
	}
	
	public void quitarServicioACGP(CGP cgp, ServicioDeCGP servicio){
		cgp.quitarServicio(servicio);
	}
	
	public void agregarVerticeCGP(CGP cgp, Posicion verticeNuevo){
		cgp.agregarVertice(verticeNuevo);
	}
	
	public void quitarVerticeCGP(CGP cgp, Posicion verticeViejo){
		cgp.quitarVertice(verticeViejo);
	}
	
	public void modificarNombreBanco(SucursalBanco banco, String nombre){
		banco.setNombre(nombre);
	}
	
	public void modificarNombreSucursalBanco(SucursalBanco banco, String nombre){
		banco.setSucursal(nombre);
	}

	public void modificarNombreGerenteBanco(SucursalBanco banco, String nombre){
		banco.setGerente(nombre);
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
		



