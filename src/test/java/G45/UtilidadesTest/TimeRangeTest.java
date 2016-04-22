package G45.UtilidadesTest;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import G45.Utilidades.TimeRange;

public class TimeRangeTest {
	LocalTime unaHora;
	TimeRange unRango = new TimeRange(LocalTime.of(9,30,5),LocalTime.of(11,25,15));
	
	@Test
	public void estaDentroDelRango(){
		unaHora = LocalTime.of(10,0,0);
		Assert.assertTrue( unRango.isValidValue(unaHora));
	}
	@Test
	public void estaEnRangoInferior(){
		unaHora = LocalTime.of(9,30,5);
		Assert.assertTrue( unRango.isValidValue(unaHora));
	}
	@Test
	public void estaEnRangoSuperior(){
		unaHora = LocalTime.of(11,25,15);
		Assert.assertTrue( unRango.isValidValue(unaHora));
	}
	@Test
	public void superaElRangoDefinido(){
		unaHora = LocalTime.of(12,25,15);
		Assert.assertFalse( unRango.isValidValue(unaHora));
	}
	@Test
	public void noAlcanzaElRangoDefinido(){
		unaHora = LocalTime.of(8,25,15);
		Assert.assertFalse( unRango.isValidValue(unaHora));
	}
	
}
