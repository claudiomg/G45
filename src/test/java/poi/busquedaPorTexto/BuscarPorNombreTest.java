package poi.busquedaPorTexto;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.Kiosco;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.usuario.Usuario;
import poi.utilidades.Consulta;
import poi.utilidades.RepositorioPOI;

public class BuscarPorNombreTest {

	RepositorioPOI repositorio = RepositorioPOI.getInstance();
	Usuario unUsuario = new Usuario();
	
	ArrayList<String> etiquetasColectivo = new ArrayList<String>();
	ArrayList<String> etiquetasCGP = new ArrayList<String>();
	ArrayList<String> etiquetasKiosco= new ArrayList<String>();
	
	POI paradaColectivo = new ParadaColectivo(etiquetasColectivo,null);
	POI CGP1 = new CGP(etiquetasCGP, null, null);
	LocalComercial kiosco = new Kiosco();
	
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
		repositorio.agregarPOI(paradaColectivo);
		repositorio.agregarPOI(kiosco);
		repositorio.agregarPOI(CGP1);
	}
	
	@After
	public void vaciarRepositorio(){
		repositorio.removerPOI(paradaColectivo);
		repositorio.removerPOI(kiosco);
		repositorio.removerPOI(CGP1);
	}
	
	@Test
	public void buscarColectivo7(){
		Consulta consulta7 =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consulta7);		
		unUsuario.buscarPOIPorPalabra("7");
		Assert.assertEquals(unUsuario.getConsultaActiva().getPoisEncontrados().size(), 1);
	}
	
	@Test
	public void buscarPorFlores(){
		Consulta consultaFlores =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaFlores);		
		unUsuario.buscarPOIPorPalabra("flores");		
		Assert.assertEquals(unUsuario.getConsultaActiva().getPoisEncontrados().size(), 1);		
	}
	
	@Test
	public void buscarPorFotocopias(){
		Consulta consultaFotocopias =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaFotocopias);		
		unUsuario.buscarPOIPorPalabra("fotocopias");		
		Assert.assertEquals(unUsuario.getConsultaActiva().getPoisEncontrados().size(), 1);		
	}
}
