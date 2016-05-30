package poi.DisponiblidadTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.utilidades.TimeRange;
import poi.utilidades.DisponibilidadHoraria;

public class DisponibilidadNueva {
	LocalTime unaHora;
	DayOfWeek unDia;
	TimeRange rangoInferior = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperior = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	TimeRange otroRango = new TimeRange (LocalTime.of(8, 30, 0),LocalTime.of(16, 0, 0));
	DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
	DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
	DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
	LocalComercial unKiosco = new LocalComercial(RadioCercania.Kiosco);
	
	@Before
	public void inicializarEscenario(){
		lunes.agregarNuevoRango(rangoInferior);
		lunes.agregarNuevoRango(rangoSuperior);
		martes.agregarNuevoRango(rangoInferior);
		martes.agregarNuevoRango(rangoSuperior);
		jueves.agregarNuevoRango(otroRango);
		unKiosco.setDisponibilidadHoraria(lunes);
		unKiosco.setDisponibilidadHoraria(martes);
		unKiosco.setDisponibilidadHoraria(jueves);
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10amElMartes(){
		unDia = DayOfWeek.TUESDAY;
		unaHora = LocalTime.of(9, 0, 0);
		Assert.assertFalse(unKiosco.estaDisponible1(unaHora, unDia));
		
	}
	
	@Test
	public void estaDisponibleALas10amElMartes(){
		unDia = DayOfWeek.TUESDAY;
		unaHora = LocalTime.of(10, 0, 0);
		Assert.assertTrue(unKiosco.estaDisponible1(unaHora, unDia));
		
	}
	
	@Test
	public void noEstaDisponibleEntreLas13yLas17ElMartes(){
		unDia = DayOfWeek.TUESDAY;
		unaHora = LocalTime.of(15, 0, 0);
		Assert.assertFalse(unKiosco.estaDisponible1(unaHora, unDia));
		
	}
	
	@Test
	public void estaDisponibleAntesDeLas10amElJueves(){
		unDia = DayOfWeek.THURSDAY;
		unaHora = LocalTime.of(9, 0, 0);
		Assert.assertTrue(unKiosco.estaDisponible1(unaHora, unDia));
		
	}

}
