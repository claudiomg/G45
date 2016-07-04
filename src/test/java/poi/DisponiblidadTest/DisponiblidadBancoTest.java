package poi.DisponiblidadTest;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.Posicion;

public class DisponiblidadBancoTest {
	Direccion direccion = new Direccion();
	Posicion posicion = new Posicion(40.417, -3.703);
	SucursalBanco unaSucursalDeBanco = new SucursalBanco("banco",posicion,direccion);
	LocalDateTime unaFechaHora;
	ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();
	
	@Before
	public void inicializar(){
		unaSucursalDeBanco.setFeriados(feriados);
	}
	
	@Test
	public void noEstaDisponibleAntesDeLas10am(){
		unaFechaHora = LocalDateTime.of(2016, 07, 4, 03, 00,00);
		Assert.assertFalse( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}

	@Test
	public void estaDisponibleALas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 10, 00);
		Assert.assertTrue( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas12am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 12, 00);
		Assert.assertTrue( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas15am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 15, 00);
		Assert.assertTrue( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleDespuesDeLas15pm(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 16, 35);
		Assert.assertFalse( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElLunes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 18, 12, 00);
		Assert.assertTrue( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElViernes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 22, 12, 00);
		Assert.assertTrue( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnSabado(){
		unaFechaHora = LocalDateTime.of(2016, 04, 23, 12, 00);
		Assert.assertFalse( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnDomingo(){
		unaFechaHora = LocalDateTime.of(2016, 04, 24, 12, 00);
		Assert.assertFalse( unaSucursalDeBanco.estaDisponible(unaFechaHora));
	}
	
}
