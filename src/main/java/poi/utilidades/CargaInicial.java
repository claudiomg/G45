package poi.utilidades;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;
import poi.repositorios.RepositorioUsuarios;

public class CargaInicial {
	public static CargaInicial instance = null;
	private HashMap<String, Direccion> direcciones = new HashMap<String, Direccion>();
	private HashMap<String, Posicion> posiciones = new HashMap<String, Posicion>();
	
	private CargaInicial(){		
	};
	public static CargaInicial getInstance() {
		if(instance == null) {
			instance = new CargaInicial();
		}
		return instance;
	}
	
	public void inicializar(){
		
		this.inicializarUsuarios();
		this.inicializarDirecciones();
		this.inicializarPosiciones();
		this.inicializarPOIs();
		this.inicializarBancosExternos();
		this.inicializarCgpExternos();
		this.inicializarConsultas();
		/** CREAR USR_DEFAULT **/
		RepositorioAbstractoPOI repoPOI = RepositorioPOI.getInstance();
		
		
		
		ArrayList<String> arrayEtiquetas = new ArrayList<String>();
		Posicion posicion1 = new Posicion(40.417, -3.703);
		Posicion posicion2 = new Posicion(-34.670741, -58.465881);
		Posicion posicion3 = new Posicion(-34.6708, -58.4657);
		Posicion posicion4 = new Posicion(-34.6706, -58.4659);
		POI parada1 = new ParadaColectivo(arrayEtiquetas,posicion1);		
		POI parada2 = new ParadaColectivo(arrayEtiquetas,posicion3);
		parada1.setNombre("Parada 152 - 6");
		parada2.setNombre("Parada 64 - 8");
		usr.setPosicion(posicion2);
		
		repoPOI.pois.add(parada1);
		repoPOI.pois.add(parada2);
		
		TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(4,0,0),LocalTime.of(4,30,0));
		TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(6,0,0),LocalTime.of(6,30,0));
		TimeRange rangoDelSabado = new TimeRange (LocalTime.of(4, 30, 0),LocalTime.of(4, 45, 0));
		DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
		DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
		DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
		DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
		DisponibilidadHoraria viernes = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
		DisponibilidadHoraria sabado = new DisponibilidadHoraria (DayOfWeek.SATURDAY);
		POI unKiosco = new LocalComercial(RadioCercania.Kiosco);
		lunes.agregarNuevoRango(rangoInferiorDeLaSemana);
		lunes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		martes.agregarNuevoRango(rangoInferiorDeLaSemana);
		martes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoInferiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		sabado.agregarNuevoRango(rangoDelSabado);
		unKiosco.addDisponibilidadDeAtencion(lunes);
		unKiosco.addDisponibilidadDeAtencion(martes);
		unKiosco.addDisponibilidadDeAtencion(miercoles);
		unKiosco.addDisponibilidadDeAtencion(jueves);
		unKiosco.addDisponibilidadDeAtencion(viernes);
		unKiosco.addDisponibilidadDeAtencion(sabado);
		unKiosco.setNombre("El kiosco de Luisito");
		unKiosco.setPosicion(posicion4);
		repoPOI.pois.add(unKiosco);
	}
	private void inicializarPosiciones() {
		this.posiciones.put("PlazaItalia", new Posicion(-34.58108,-58.42105));
		this.posiciones.put("SantaFe3259", new Posicion(-34.588304,-58.410962));
		this.posiciones.put("SantaFe3354", new Posicion(-34.587644,-58.41377));
		this.posiciones.put("SantaFe3025", new Posicion(-34.590771,-58.407982));
		
		this.posiciones.put("Rivadavia5010", new Posicion(-34.618473,-58.436714));
		this.posiciones.put("Rivadavia5157", new Posicion(-34.619155,-58.438446));
		this.posiciones.put("Rivadavia5243", new Posicion(-34.619637,-58.439476));
		this.posiciones.put("Rivadavia4975", new Posicion(-34.617908,-58.435673));
	}
	private void inicializarDirecciones() {
		this.createDireccion("PlazaItalia","Palermo","Circular Plaza Italia","4090","C1425BHP");
		this.createDireccion("SantaFe3259","Palermo","Av. Santa Fe","3259","C1425BHP");
		this.createDireccion("SantaFe3354","Palermo","Av. Santa Fe","3354","C1425BHP");
		this.createDireccion("SantaFe3025","Palermo","Av. Santa Fe","3025","C1425BHP");
		
		this.createDireccion("Rivadavia5010","Caballito","Av. Rivadavia","5010","C1424CES");
		this.createDireccion("Rivadavia5157","Caballito","Av. Rivadavia","5157","C1424CES");
		this.createDireccion("Rivadavia5243","Caballito","Av. Rivadavia","5243","C1424CES");
		this.createDireccion("Rivadavia4975","Caballito","Av. Rivadavia","4975","C1424CES");

	}
	
	private void inicializarPOIs() {
		RepositorioPOI repositorio = RepositorioPOI.getInstance();
		//parada colectivos
		ParadaColectivo parada;
		
		
		//Linea 36
		parada = new ParadaColectivo("Linea 36", new Posicion(-34.58108, -58.42105), this.getDireccion("PlazaItalia"));
	}
	private void inicializarUsuarios() {
		RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
		UsuarioPOI usuario;
		
		//admin1
		usuario = new Administrador("admin1");
		usuario.setPassword("admin1");
		((Administrador) usuario).setMail("mujica.juancarlos@gmail.com");
		repositorio.agregarRegistro(usuario);
		
		//admin2
		usuario = new Administrador("admin2");
		usuario.setPassword("admin2");
		((Administrador) usuario).setMail("lalala@gmail.com");
		repositorio.agregarRegistro(usuario);
			
		//terminal1
		usuario = new Terminal("Abasto");
		usuario.setPassword("abasto");
		((Terminal) usuario).setPosicion(new Posicion(-34.603329, -58.410789));
		repositorio.agregarRegistro(usuario);
		
		//terminal2
		usuario = new Terminal("Caballito");
		usuario.setPassword("caballito");
		((Terminal) usuario).setPosicion(new Posicion(-34.619155, -58.437811));
		repositorio.agregarRegistro(usuario);
		
		//terminal3
		usuario = new Terminal("Palermo");
		usuario.setPassword("palermo");
		((Terminal) usuario).setPosicion(new Posicion(-34.588315, -58.410690));
		repositorio.agregarRegistro(usuario);
	}
	private void createDireccion(String key, String barrio, String calle, String altura, String cp) {
		Direccion direccion = new Direccion();
		direccion.setBarrio(barrio);
		direccion.setCalle(calle);
		direccion.setNumero(altura);
		direccion.setCodigoPostal(cp);
		direccion.setLocalidad("Capital Federal");
		direccion.setPais("Argentina");
		this.setDireccion(key,direccion);		
	}
	private void setDireccion(String key, Direccion direccion) {
		this.direcciones.put(key, direccion);
	}
	private Direccion getDireccion(String key) {
		return this.direcciones.get(key);
	}
	private Posicion getPosicion(String key) {
		return this.posiciones.get(key);
	}
}
