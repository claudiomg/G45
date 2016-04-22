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
	
	@Before
	public void inicializarEscenario(){
		unServicio.agregarDiaDeAtencion(DayOfWeek.MONDAY);
		unServicio.agregarDiaDeAtencion(DayOfWeek.TUESDAY);
		unServicio.agregarDiaDeAtencion(DayOfWeek.WEDNESDAY);
		unServicio.agregarDiaDeAtencion(DayOfWeek.THURSDAY);
		unServicio.agregarDiaDeAtencion(DayOfWeek.FRIDAY);
		unServicio.agregarHorarioDeAtencion(rangoInferior);
		unServicio.agregarHorarioDeAtencion(rangoSuperior);
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
