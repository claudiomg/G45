import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.CGP;
import poi.Kiosco;
import poi.LibreriaEscolar;
import poi.POI;
import poi.ParadaColectivo;
import usuario.Posicion;
import usuario.Usuario;

public class CercaniaTest {

	Posicion posicionUno = new Posicion(40.417, -3.703);
	Posicion posicionDos = new Posicion(40.453, -3.68);		
	Posicion posicionTres = new Posicion(40.417, -3.702);
	Posicion posicionCuatro = new Posicion(40.417, -3.68);
	Posicion posicionCinco = new Posicion(40.453, -3.703);
	Posicion posicionSeis = new Posicion(40.400, -3.730);	
	Usuario unUsuario = new Usuario();
	POI unPOI = new ParadaColectivo(); 
	List<Posicion> vertices = new ArrayList<Posicion>();
	
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionUno);
		vertices.add(posicionUno);
		vertices.add(posicionDos);
		vertices.add(posicionCuatro);
		vertices.add(posicionCinco);
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
	public void unCGPEstaLejos(){
		unUsuario.setPosicion(posicionSeis);
		//Posicion 6 por fuera del area de cobertura del CGP
		unPOI = new CGP(vertices); 
		Assert.assertFalse(unUsuario.estoyCercaDe(unPOI));
	}	

	@Test
	public void unCGPEstaCerca(){
		//Posicion 1 en el borde del area de cobertura del CGP
		unPOI = new CGP(vertices); 
		Assert.assertTrue(unUsuario.estoyCercaDe(unPOI));
	}

	@Test
	public void unKioscoEstaLejos(){
		//Distancia entre puntos 4,45 kms
		unPOI = new Kiosco(); 
		unPOI.setPosicion(posicionDos);
		Assert.assertFalse(unUsuario.estoyCercaDe(unPOI));
	}	

	@Test
	public void unKioscoEstaCerca(){
		//Distancia entre puntos 0,08 kms
		unPOI = new Kiosco(); 
		unPOI.setPosicion(posicionTres);
		Assert.assertTrue(unUsuario.estoyCercaDe(unPOI));
	}

	@Test
	public void unaLibreriaEscolarEstaLejos(){
		//Distancia entre puntos 4,45 kms
		unPOI = new LibreriaEscolar();
		unPOI.setPosicion(posicionDos);
		Assert.assertFalse(unUsuario.estoyCercaDe(unPOI));
	}	

	@Test
	public void unLibreriaEscolarEstaCerca(){
		//Distancia entre puntos 0,08 kms
		unPOI = new LibreriaEscolar();
		unPOI.setPosicion(posicionTres);
		Assert.assertTrue(unUsuario.estoyCercaDe(unPOI));
	}
}
