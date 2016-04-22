package poi.DisponiblidadTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import G45.Utilidades.TimeRange;
import poi.Kiosco;
import poi.LocalComercial;

public class DisponiblidadLocalComercialTest {
	
	LocalDateTime unaFechaHora;
	TimeRange rangoInferior = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperior = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	LocalComercial unKiosco = new LocalComercial(new Kiosco());
	
	@Before
	public void inicializarEscenario(){
		unKiosco.agregarDiaDeAtencion(DayOfWeek.MONDAY);
		unKiosco.agregarDiaDeAtencion(DayOfWeek.TUESDAY);
		unKiosco.agregarDiaDeAtencion(DayOfWeek.WEDNESDAY);
		unKiosco.agregarDiaDeAtencion(DayOfWeek.THURSDAY);
		unKiosco.agregarDiaDeAtencion(DayOfWeek.FRIDAY);
		unKiosco.agregarHorarioDeAtencion(rangoInferior);
		unKiosco.agregarHorarioDeAtencion(rangoSuperior);
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 03, 00);
		Assert.assertFalse( unKiosco.estaDisponible(unaFechaHora));
	}
	
	@Test
	public void estaDisponibleALas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 10, 00);
		Assert.assertTrue( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas12am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 12, 00);
		Assert.assertTrue( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas17am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 17, 00);
		Assert.assertTrue( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleALas15pm(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 15, 00);
		Assert.assertFalse( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElLunes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 18, 12, 00);
		Assert.assertTrue( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElViernes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 22, 12, 00);
		Assert.assertTrue( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnSabado(){
		unaFechaHora = LocalDateTime.of(2016, 04, 23, 12, 00);
		Assert.assertFalse( unKiosco.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnDomingo(){
		unaFechaHora = LocalDateTime.of(2016, 04, 24, 12, 00);
		Assert.assertFalse( unKiosco.estaDisponible(unaFechaHora));
	}
}
