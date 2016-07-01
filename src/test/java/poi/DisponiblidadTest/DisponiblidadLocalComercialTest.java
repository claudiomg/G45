package poi.DisponiblidadTest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.acciones.Accion;
import poi.acciones.EstaDisponible;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.ExcepcionHorarioCambiado;
import poi.utilidades.TimeRange;
import poi.utilidades.ExcepcionSinAtencion;

public class DisponiblidadLocalComercialTest {

	TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	TimeRange rangoDelSabado = new TimeRange (LocalTime.of(8, 30, 0),LocalTime.of(16, 0, 0));
	TimeRange rangoCambiado = new TimeRange (LocalTime.of(10, 30, 0),LocalTime.of(16, 0, 0));
	DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
	DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
	DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
	DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
	DisponibilidadHoraria viernes = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
	DisponibilidadHoraria sabado = new DisponibilidadHoraria (DayOfWeek.SATURDAY);
	LocalComercial unKiosco = new LocalComercial(RadioCercania.Kiosco);
	ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();
	ExcepcionHorarioCambiado horarioCambiado = new ExcepcionHorarioCambiado(LocalDate.of(2016, 6, 2));
		
	@Before
	public void inicializarEscenario(){
		horarioCambiado.agregarRangoCambiado(rangoCambiado);
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
		unKiosco.agregarHorarioCambiado(horarioCambiado);
		feriados.agregarFeriados(LocalDateTime.of(0, 7, 9, 0, 0));
		feriados.agregarFeriados(LocalDateTime.of(0, 5, 1, 0, 0));
		unKiosco.setFeriados(feriados);
	
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10amElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 9, 59,59);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad() );
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas1030amElJueves2dejunio(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 2, 10, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad() );
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas16pmElJueves2dejunio(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 2, 16, 0,01);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad() );
	}
	
	@Test
	public void estaDisponibleA12pmElJueves2dejunio(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 2, 12, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad() );
	}

	@Test
	public void noEstaDisponibleAntesDeLas10amElMiercoles2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 1, 9, 30,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALas10amElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 10, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleDespuesDeLas10amYAntesDeLa1pmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 11, 30,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALa1pmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 13, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLa1pmYAntesDeLas5pmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 13, 0,01);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleEntreLa1pmyLas5pmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 15, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleEntreLa1pmyLas5pmElMiercoles2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 16, 59,59);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALas5pmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 17, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleEntreLas5pmyLas8yMediapmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 1, 18, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleEntreLas5pmyLas8YMediapmElMiercoles2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 18, 15,25);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALas8yMediapmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 20, 30,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDespuesDeLas8yMediapmElMiercoles(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 20, 30,01);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas8yMediapmElMiercoles2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 01, 23, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas8yMediaAmElSabado(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 8, 29,59);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas8yMediaAmElSabado2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 7, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALas8yMediaAmElSabado(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 8, 30,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleDespuesDeLas8yMediaAmElSabado(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 8, 30,01);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALas8yMediaAmElSabado2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 14, 30,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleAntesDeLas4pmSabado(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 15, 59,59);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALas4pmElSabado(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 16, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas4pmElSabado(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 16, 0,01);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleDespuesDeLas4pmElSabado2(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 6, 4, 19, 30,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void noEstaDisponibleEl9dejulio(){
		LocalDateTime unaFechaHora = LocalDateTime.of(2016, 7, 9, 10, 0,0);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unKiosco,unaFechaHora);
		agregarYEjecutarAccion(unKiosco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad() );
	}
	
	private void agregarYEjecutarAccion(LocalComercial unKiosco,
			EstaDisponible estaDisponibleTest) {
		unKiosco.agregarAccion(estaDisponibleTest);
		List<Accion> accionesBanco = unKiosco.acciones;
		for (Accion accion : accionesBanco){
			accion.ejecutarAccion();
		}
		
	}
	
}
