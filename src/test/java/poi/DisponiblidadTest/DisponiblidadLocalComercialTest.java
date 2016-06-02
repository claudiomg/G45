package poi.DisponiblidadTest;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.TimeRange;

public class DisponiblidadLocalComercialTest {
	
	LocalTime unaHora;
	DayOfWeek unDia;
	TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	TimeRange rangoDelSabado = new TimeRange (LocalTime.of(8, 30, 0),LocalTime.of(16, 0, 0));
	DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
	DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
	DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
	DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
	DisponibilidadHoraria viernes = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
	DisponibilidadHoraria sabado = new DisponibilidadHoraria (DayOfWeek.SATURDAY);
	LocalComercial unKiosco = new LocalComercial(RadioCercania.Kiosco);
		
	@Before
	public void inicializarEscenario(){
		lunes.agregarNuevoRango(rangoInferiorDeLaSemana);
		lunes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		martes.agregarNuevoRango(rangoInferiorDeLaSemana);
		martes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoInferiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoInferiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoSuperiorDeLaSemana);
		viernes.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		sabado.agregarNuevoRango(rangoDelSabado);
		unKiosco.setDisponibilidadHoraria(lunes);
		unKiosco.setDisponibilidadHoraria(martes);
		unKiosco.setDisponibilidadHoraria(miercoles);
		unKiosco.setDisponibilidadHoraria(jueves);
		unKiosco.setDisponibilidadHoraria(viernes);
		unKiosco.setDisponibilidadHoraria(sabado);
	
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10amElMiercoles(){
		unaHora = LocalTime.of(9, 59, 59);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10amElMiercoles2(){
		unaHora = LocalTime.of(9, 30, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALas10amElMiercoles(){
		unaHora = LocalTime.of(10, 0, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleDespuesDeLas10amYAntesDeLa1pmElMiercoles(){
		unaHora = LocalTime.of(11, 30, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALa1pmElMiercoles(){
		unaHora = LocalTime.of(13, 0, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLa1pmYAntesDeLas5pmElMiercoles(){
		unaHora = LocalTime.of(13, 0, 1);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleEntreLa1pmyLas5pmElMiercoles(){
		unaHora = LocalTime.of(15, 0, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleEntreLa1pmyLas5pmElMiercoles2(){
		unaHora = LocalTime.of(16, 59, 59);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALas5pmElMiercoles(){
		unaHora = LocalTime.of(17, 0, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleEntreLas5pmyLas8yMediapmElMiercoles(){
		unaHora = LocalTime.of(17, 0, 1);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleEntreLas5pmyLas8YMediapmElMiercoles2(){
		unaHora = LocalTime.of(18, 15, 25);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALas8yMediapmElMiercoles(){
		unaHora = LocalTime.of(20, 30, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDespuesDeLas8yMediapmElMiercoles(){
		unaHora = LocalTime.of(20, 30, 1);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas8yMediapmElMiercoles2(){
		unaHora = LocalTime.of(23, 0, 0);
		unDia= DayOfWeek.WEDNESDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas8yMediaAmElSabado(){
		unaHora = LocalTime.of(8, 29, 59);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas8yMediaAmElSabado2(){
		unaHora = LocalTime.of(7,0 , 0);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALas8yMediaAmElSabado(){
		unaHora = LocalTime.of(8, 30, 0);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleDespuesDeLas8yMediaAmElSabado(){
		unaHora = LocalTime.of(8, 30, 1);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALas8yMediaAmElSabado2(){
		unaHora = LocalTime.of(14, 30, 0);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleAntesDeLas4pmSabado(){
		unaHora = LocalTime.of(15, 59, 59);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void estaDisponibleALas4pmElSabado(){
		unaHora = LocalTime.of(16, 0, 0);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertTrue( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas4pmElSabado(){
		unaHora = LocalTime.of(16, 0, 1);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas4pmElSabado2(){
		unaHora = LocalTime.of(19, 30, 0);
		unDia= DayOfWeek.SATURDAY;
		
		Assert.assertFalse( unKiosco.estaDisponible(unaHora, unDia));
	}
	
}
