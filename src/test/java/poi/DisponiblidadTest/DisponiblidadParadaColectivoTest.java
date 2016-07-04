package poi.DisponiblidadTest;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class DisponiblidadParadaColectivoTest {
	Posicion unaPosicion = new Posicion(40.417, -3.703);
	Direccion direccion = new Direccion();
	ParadaColectivo colectivo = new ParadaColectivo("parada1",unaPosicion,direccion);
	LocalDateTime fechaHora;
	
	@Test
	public void estaDisponibleDeMadrugada(){
		fechaHora = LocalDateTime.of(2016, 06, 25, 3, 01);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}
	
	@Test
	public void estaDisponibleALaManiana(){
		fechaHora = LocalDateTime.of(2016, 06, 25, 9, 25);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}
	@Test
	public void estaDisponibleALaTarde(){
		fechaHora = LocalDateTime.of(2016, 06, 25, 16, 35);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}
	@Test
	public void estaDisponibleALaNoche(){
		fechaHora = LocalDateTime.of(2016, 06, 25, 20, 41);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}
	@Test
	public void estaDisponibleUnSabado(){
		fechaHora = LocalDateTime.of(2016, 04, 23, 10, 00);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}
	@Test
	public void estaDisponibleUnDiaDeSemana(){
		fechaHora = LocalDateTime.of(2016, 04, 17, 11, 00);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}
	@Test
	public void estaDisponibleUnDiaFeriado(){
		fechaHora = LocalDateTime.of(2016, 01, 01, 10, 00);
		Assert.assertTrue( colectivo.estaDisponible(fechaHora));
	}	
}
