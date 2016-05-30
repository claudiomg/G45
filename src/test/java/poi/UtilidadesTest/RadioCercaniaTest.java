package poi.UtilidadesTest;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.RadioCercania;

public class RadioCercaniaTest {
	
	private Double rango;
	private RadioCercania radioCercania;
	

	@Test
	public void elRadioCercaniaDeUnKioscoEsCeroPuntoDos(){
		rango = 0.2;
		radioCercania = RadioCercania.Kiosco;
		Assert.assertTrue( rango.equals(radioCercania.getValue()));
	}
	@Test
	public void elRadioCercaniaDeUnaLibreriaEsCeroPuntoCinco(){
		rango = 0.5;
		radioCercania = RadioCercania.LibreriaEscolar;
		Assert.assertTrue( rango.equals(radioCercania.getValue()));
	}
}
