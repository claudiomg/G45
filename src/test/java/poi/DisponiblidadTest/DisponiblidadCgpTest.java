package poi.DisponiblidadTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.utilidades.Posicion;

public class DisponiblidadCgpTest {
	ArrayList<String> arrayEtiquetas = new ArrayList<String>();
	List<Posicion> vertices = new ArrayList<Posicion>();
	Posicion unaPosicion = new Posicion(40.417, -3.703);
	CGP unaCGP = new CGP(arrayEtiquetas,unaPosicion,vertices);
	LocalDateTime unaFechaHora;
	
	@Test
	public void noEstaDisponibleAntesDeLas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 03, 00);
		Assert.assertFalse( unaCGP.estaDisponible(unaFechaHora));
	}
	
	@Test
	public void estaDisponibleALas10am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 10, 00);
		Assert.assertTrue( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas12am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 12, 00);
		Assert.assertTrue( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleALas15am(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 15, 00);
		Assert.assertTrue( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleDespuesDeLas15pm(){
		unaFechaHora = LocalDateTime.of(2016, 04, 20, 16, 35);
		Assert.assertFalse( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElLunes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 18, 12, 00);
		Assert.assertTrue( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void estaDisponibleElViernes(){
		unaFechaHora = LocalDateTime.of(2016, 04, 22, 12, 00);
		Assert.assertTrue( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnSabado(){
		unaFechaHora = LocalDateTime.of(2016, 04, 23, 12, 00);
		Assert.assertFalse( unaCGP.estaDisponible(unaFechaHora));
	}
	@Test
	public void noEstaDisponibleUnDomingo(){
		unaFechaHora = LocalDateTime.of(2016, 04, 24, 12, 00);
		Assert.assertFalse( unaCGP.estaDisponible(unaFechaHora));
	}
}
