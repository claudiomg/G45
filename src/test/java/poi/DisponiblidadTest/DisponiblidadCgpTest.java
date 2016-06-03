package poi.DisponiblidadTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Feriados;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class DisponiblidadCgpTest {
	ArrayList<String> arrayEtiquetas = new ArrayList<String>();
	List<Posicion> vertices = new ArrayList<Posicion>();
	Posicion unaPosicion = new Posicion(40.417, -3.703);
	TimeRange rangoInferior = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperior = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	CGP unaCGP = new CGP(arrayEtiquetas,unaPosicion,vertices);
	ServicioDeCGP unServicio = new ServicioDeCGP();
	LocalDateTime unaFechaHora;
	Feriados feriados = new Feriados();
	
	@Before
	public void inicializarEscenario(){
		feriados.agregarFeriados(LocalDateTime.of(0, 7, 9, 0, 0));
		feriados.agregarFeriados(LocalDateTime.of(0, 5, 1, 0, 0));
		unServicio.setFeriados(feriados);
		
		DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
		lunes.agregarNuevoRango(rangoInferior);
		lunes.agregarNuevoRango(rangoSuperior);
		unServicio.agregarDisponibilidadDeAtencion(lunes);
		
		DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
		martes.agregarNuevoRango(rangoInferior);
		martes.agregarNuevoRango(rangoSuperior);
		unServicio.agregarDisponibilidadDeAtencion(martes);
		
		DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
		miercoles.agregarNuevoRango(rangoInferior);
		miercoles.agregarNuevoRango(rangoSuperior);
		unServicio.agregarDisponibilidadDeAtencion(miercoles);
		
		DisponibilidadHoraria jueves = new DisponibilidadHoraria(DayOfWeek.THURSDAY);
		jueves.agregarNuevoRango(rangoInferior);
		jueves.agregarNuevoRango(rangoSuperior);
		unServicio.agregarDisponibilidadDeAtencion(jueves);
		
		DisponibilidadHoraria viernes = new DisponibilidadHoraria(DayOfWeek.FRIDAY);
		viernes.agregarNuevoRango(rangoInferior);
		viernes.agregarNuevoRango(rangoSuperior);
		unServicio.agregarDisponibilidadDeAtencion(viernes);
	}

	@Test
	public void noEstaDisponibleNoTieneServiciosDisponible(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 03, 00);
		//la coleccion de servicios esta vacia
		Assert.assertFalse( unaCGP.estaDisponible(unaFechaHora));
	}
	
	@Test
	public void noEstaDisponibleTieneServiciosNoDisponibles(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 14, 00);
		unaCGP.agregarServicio(unServicio);
		//la coleccion de servicios esta vacia
		Assert.assertFalse( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponiblePorTenerServicioDisponible(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 12, 00);
		unaCGP.agregarServicio(unServicio);
		Assert.assertTrue( unaCGP.estaDisponible(unaFechaHora));
	}
}
