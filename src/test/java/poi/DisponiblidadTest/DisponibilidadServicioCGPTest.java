package poi.DisponiblidadTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.utilidades.TimeRange;

public class DisponibilidadServicioCGPTest {
	ServicioDeCGP unServicio = new ServicioDeCGP();
	TimeRange rangoInferior = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperior = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
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
	public void noEstaDisponibleAntesDeLas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 03, 00);
		Assert.assertFalse( unServicio.estaDisponible(unaFechaHora));
	}
	
	@Test
	public void estaDisponibleALas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 10, 00);
		Assert.assertTrue( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas12am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 12, 00);
		Assert.assertTrue( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas17am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 17, 00);
		Assert.assertTrue( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleALas15pm(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 15, 00);
		Assert.assertFalse( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElLunes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 18, 12, 00);
		Assert.assertTrue( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElViernes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 22, 12, 00);
		Assert.assertTrue( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnSabado(){
		unaFechaHora = LocalDateTime.of(2016, 04, 23, 12, 00);
		Assert.assertFalse( unServicio.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnDomingo(){
		unaFechaHora = LocalDateTime.of(2016, 04, 24, 12, 00);
		Assert.assertFalse( unServicio.estaDisponible(unaFechaHora));
	}

}
