package Disponiblidad;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import poi.ParadaColectivo;
import usuario.Posicion;

public class DisponiblidadParadaColectivoTest {
	
	ArrayList<String> arrayEtiquetas = new ArrayList<String>();
	Posicion unaPosicion = new Posicion(40.417, -3.703);
	ParadaColectivo unaParadaDeColectivo = new ParadaColectivo(arrayEtiquetas,unaPosicion);
	LocalDateTime unaHora;
	
	@Test
	public void estaDisponibleDeMadrugada(){
		unaHora = LocalDateTime.of(2016, 06, 25, 3, 01);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	
	@Test
	public void estaDisponibleALaManiana(){
		unaHora = LocalDateTime.of(2016, 06, 25, 9, 25);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	@Test
	public void estaDisponibleALaTarde(){
		unaHora = LocalDateTime.of(2016, 06, 25, 16, 35);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	@Test
	public void estaDisponibleALaNoche(){
		unaHora = LocalDateTime.of(2016, 06, 25, 20, 41);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	@Test
	public void estaDisponibleUnSabado(){
		unaHora = LocalDateTime.of(2016, 04, 23, 10, 00);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	@Test
	public void estaDisponibleUnDiaDeSemana(){
		unaHora = LocalDateTime.of(2016, 04, 17, 11, 00);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	@Test
	public void estaDisponibleUnDiaFeriado(){
		unaHora = LocalDateTime.of(2016, 01, 01, 10, 00);
		Assert.assertTrue( unaParadaDeColectivo.estaDisponible(unaHora));
	}
	
}
