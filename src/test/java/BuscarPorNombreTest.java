import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.CGP;
import poi.Kiosco;
import poi.POI;
import poi.ParadaColectivo;
import usuario.Posicion;
import usuario.Usuario;


public class BuscarPorNombreTest {

	Posicion posicionUno = new Posicion(40.417, -3.703);
	Posicion posicionDos = new Posicion(40.453, -3.68);		
	Posicion posicionTres = new Posicion(40.417, -3.702);
	Posicion posicionCuatro = new Posicion(40.417, -3.68);
	Usuario unUsuario = new Usuario();
	ArrayList<POI> ListaPuntos = new ArrayList<POI>();
	
	ArrayList<String> etiquetasColectivo = new ArrayList<String>();
	ArrayList<String> etiquetasCGP = new ArrayList<String>();
	ArrayList<String> etiquetasKiosco= new ArrayList<String>();
	
	POI paradaColectivo = new ParadaColectivo(etiquetasColectivo,null);
	POI CGP1 = new CGP(etiquetasCGP, null, null);
	POI kiosco = new Kiosco();
	
	@Before
	public void inicializar(){


		paradaColectivo.agregarEtiqueta("7");
		paradaColectivo.agregarEtiqueta("retiro");
		paradaColectivo.agregarEtiqueta("samore");
		
		CGP1.agregarEtiqueta("comuna 7");
		CGP1.agregarEtiqueta("flores");
		CGP1.agregarEtiqueta("parque chacabuco");
		
		kiosco.etiquetas = etiquetasKiosco;
		kiosco.agregarEtiqueta("golosinas");
		kiosco.agregarEtiqueta("cigarrillos");
		kiosco.agregarEtiqueta("fotocopias");
		
		ListaPuntos.add(paradaColectivo);
		ListaPuntos.add(CGP1);
		ListaPuntos.add(kiosco);
	}
	
	@Test
	public void buscarColectivo7(){
		Assert.assertTrue(unUsuario.buscarPOIPorPalabra("7", ListaPuntos ).contains(paradaColectivo));
	}
	
	@Test
	public void buscarPorFlores(){
		Assert.assertTrue(unUsuario.buscarPOIPorPalabra("flores", ListaPuntos).contains(CGP1));
	}
	
	@Test
	public void buscarPorFotocopias(){
		Assert.assertTrue(unUsuario.buscarPOIPorPalabra("fotocopias", ListaPuntos).contains(kiosco));
	}
}
