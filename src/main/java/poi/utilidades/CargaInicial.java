package poi.utilidades;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.RepositorioAdministrador;
import poi.modelo.usuario.RepositorioTerminal;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;

public class CargaInicial {
	public static CargaInicial instance = null;

	private CargaInicial(){		
	};

	public static CargaInicial getInstance() {
		if(instance == null) {
			instance = new CargaInicial();
		}
		return instance;
	}
	
	public void inicializar(){
		/** CREAR USR_DEFAULT **/
		RepositorioAbstractoPOI repoPOI = RepositorioPOI.getInstance();
		
		Terminal usr = new Terminal();
		Administrador adm = new Administrador(repoPOI);
		
		RepositorioAdministrador repoAdm = RepositorioAdministrador.getInstance();
		
		adm.setLogin("Claudio02");
		adm.setPassword("12345");
		repoAdm.administradores.add(adm);
		
		usr.setLogin("Kevin01");
		usr.setPassword("Abc123");
		RepositorioTerminal repo = RepositorioTerminal.getInstance();
		repo.terminales.add(usr);		
		
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

}
