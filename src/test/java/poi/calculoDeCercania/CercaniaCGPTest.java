package poi.calculoDeCercania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;

public class CercaniaCGPTest {

	Posicion posicionUno = new Posicion(40.417, 3.703);
	Posicion posicionDos = new Posicion(40.453, 3.68);		
	Posicion posicionTres = new Posicion(40.400, 3.702);
	Posicion posicionCuatro = new Posicion(40.417, 3.68);
	Posicion posicionCinco = new Posicion(40.453, 3.703);
	Posicion posicionSeis = new Posicion(40.430, 3.69);
	Posicion posicionSiete = new Posicion(40.417, 3.69);	
	Terminal unUsuario = new Terminal();
	RepositorioPOI repositorio;
	Consulta unaConsulta = new Consulta(repositorio);
	List<Posicion> vertices = new ArrayList<Posicion>();
	List<String> etiquetasCGP = Arrays.asList("comuna 1","puerto madero","constitucion");
	POI cgp = new CGP(etiquetasCGP, posicionUno, vertices);
	
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionSiete);
		unUsuario.agregarConsulta(unaConsulta);
		vertices.add(posicionUno);
		vertices.add(posicionDos);
		vertices.add(posicionCuatro);
		vertices.add(posicionCinco);
	}

	@Test
	public void usuarioSobreLaLineaDeBordeDeCoberturaDelCGP(){ //Sobre borde izquierdo del poligono	
		Assert.assertTrue(unUsuario.estoyCercaDe(cgp));
	}	

	@Test
	public void unCGPEstaLejos(){
		unUsuario.setPosicion(posicionTres); //Posicion 3 por fuera del area de cobertura del CGP
		Assert.assertFalse(unUsuario.estoyCercaDe(cgp));
	}	

	@Test
	public void unUsuarioEstaDentroDelAreaDeCobertura(){ //Posicion 6 dentro del area
		unUsuario.setPosicion(posicionSeis);
		Assert.assertTrue(unUsuario.estoyCercaDe(cgp));
	}

}
