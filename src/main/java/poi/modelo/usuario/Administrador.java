package poi.modelo.usuario;

import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;

import java.util.ArrayList;
import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.repositorios.RepositorioAbstractoPOI;

public class Administrador {
	
	private RepositorioAbstractoPOI repositorio;
	private Rol rol = Rol.ADMINISTRADOR;
	private String mail;
	private String login;
	private String password;
	

	public Administrador(RepositorioAbstractoPOI repositorio2){
		this.repositorio = repositorio2;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public void agregarPOI(POI poi){
		this.repositorio.agregarRegistro(poi);			
	}	
	
	public void removerPOI (POI poi){
		this.repositorio.eliminarRegistro(poi);
	}
	
	public void cambiarLatitudPOI(POI poi, double latitud){
		poi.getPosicion().setLatitud(latitud);
	}
	
	public void cambiarLongitudPOI(POI poi, double longitud){
		poi.getPosicion().setLongitud(longitud);
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
		



