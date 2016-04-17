import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.LibreriaEscolar;
import poi.POI;
import poi.ParadaColectivo;
import usuario.Posicion;
import usuario.Usuario;

public class CercaniaTest {

	Posicion posicionUno = new Posicion(40.417, -3.703);
	Posicion posicionDos = new Posicion(40.453, -3.68);		
	Posicion posicionTres = new Posicion(40.417, -3.702);		
	Usuario unUsuario = new Usuario();
	POI unPOI = new ParadaColectivo(); 
		
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionUno);
	}

	@Test
	public void unaParadaDeColectivosEstaLejos(){
		//Según Google Earth a estos valores el cálculo es igual a 4,2 km
		//Según el algoritmo utilizado 4,45 (error de solo 250 metros)
		//Distancia entre puntos 4,45 kms
		unPOI.setPosicion(posicionDos);
		Assert.assertFalse(unUsuario.estoyCercaDe(unPOI));
	}	

	@Test
	public void unaParadaDeColectivosEstaCerca(){
		//Distancia entre puntos 0,08 kms
		unPOI.setPosicion(posicionTres);
		Assert.assertTrue(unUsuario.estoyCercaDe(unPOI));
	}

	@Test
	public void unLocalComercialEstaLejos(){
		//Distancia entre puntos 4,45 kms
		unPOI = new LibreriaEscolar();
		unPOI.setPosicion(posicionDos);
		Assert.assertFalse(unUsuario.estoyCercaDe(unPOI));
	}	

	@Test
	public void unLocalComercialEstaCerca(){
		//Distancia entre puntos 0,08 kms
		unPOI.setPosicion(posicionTres);
		Assert.assertTrue(unUsuario.estoyCercaDe(unPOI));
	}

}
