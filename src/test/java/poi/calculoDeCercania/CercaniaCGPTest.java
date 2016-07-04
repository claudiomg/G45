package poi.calculoDeCercania;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.POI;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class CercaniaCGPTest {
	Direccion direccion = new Direccion();
	Posicion posicionUno = new Posicion(40.417, 3.703);
	Posicion posicionDos = new Posicion(40.453, 3.68);		
	Posicion posicionTres = new Posicion(40.400, 3.702);
	Posicion posicionCuatro = new Posicion(40.417, 3.68);
	Posicion posicionCinco = new Posicion(40.453, 3.703);
	Posicion posicionSeis = new Posicion(40.430, 3.69);
	Posicion posicionUsuario = new Posicion(40.417, 3.69);
	List<Posicion> vertices = new ArrayList<Posicion>();
	
	@Before
	public void inicializarEscenario(){
		vertices.add(posicionUno);
		vertices.add(posicionDos);
		vertices.add(posicionCuatro);
		vertices.add(posicionCinco);
	}

	@Test
	public void usuarioSobreLaLineaDeBordeDeCoberturaDelCGP(){ //Sobre borde izquierdo del poligono	
		POI cgp = new CGP("cgp", posicionUno, direccion, vertices);
		Assert.assertTrue(cgp.estaCercaDe(posicionUsuario));
	}	

	@Test
	public void unCGPEstaLejos(){
		POI cgp = new CGP("cgp", posicionUno, direccion, vertices);
		Assert.assertFalse(cgp.estaCercaDe(posicionTres));
	}	

	@Test
	public void unUsuarioEstaDentroDelAreaDeCobertura(){ //Posicion 6 dentro del area
		POI cgp = new CGP("cgp", posicionUno, direccion, vertices);
		Assert.assertTrue(cgp.estaCercaDe(posicionSeis));
	}

}
