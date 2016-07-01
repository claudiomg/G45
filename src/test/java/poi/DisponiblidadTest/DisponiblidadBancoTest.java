package poi.DisponiblidadTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.acciones.Accion;
import poi.acciones.EstaDisponible;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.Posicion;

public class DisponiblidadBancoTest {
	ArrayList<String> arrayEtiquetas = new ArrayList<String>();
	Posicion unaPosicion = new Posicion(40.417, -3.703);
	SucursalBanco unaSucursalDeBanco = new SucursalBanco(arrayEtiquetas,unaPosicion);
	LocalDateTime unaFechaHora;
	ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();
	
	
	
	@Before
	public void inicializar(){
		unaSucursalDeBanco.setFeriados(feriados);
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 03, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}

	@Test
	public void estaDisponibleALas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 10, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertTrue(estaDisponibleTest.getDisponibilidad() );
	}
	@Test
	public void estaDisponibleALas12am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 12, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleALas15am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 15, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void noEstaDisponibleDespuesDeLas15pm(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 16, 35);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleElLunes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 18, 12, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleElViernes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 22, 12, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void noEstaDisponibleUnSabado(){
		unaFechaHora = LocalDateTime.of(2016, 04, 23, 12, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void noEstaDisponibleUnDomingo(){
		unaFechaHora = LocalDateTime.of(2016, 04, 24, 12, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaSucursalDeBanco,unaFechaHora);
		agregarYEjecutarAccion(unaSucursalDeBanco,estaDisponibleTest);
		Assert.assertFalse( estaDisponibleTest.getDisponibilidad());
	}
	
	private void agregarYEjecutarAccion(SucursalBanco unaSucursalDeBanco,
			EstaDisponible estaDisponibleTest) {
		unaSucursalDeBanco.agregarAccion(estaDisponibleTest);
		List<Accion> accionesBanco = unaSucursalDeBanco.acciones;
		for (Accion accion : accionesBanco){
			accion.ejecutarAccion();
		}
		
	}
}
