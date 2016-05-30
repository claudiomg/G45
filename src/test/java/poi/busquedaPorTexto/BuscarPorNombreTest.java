package poi.busquedaPorTexto;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Usuario;
import poi.utilidades.Consulta;
import poi.utilidades.RepositorioPOI;

public class BuscarPorNombreTest {

	RepositorioPOI repositorio = RepositorioPOI.getInstance();
	Usuario unUsuario = new Usuario();
	
	ArrayList<String> etiquetasColectivo = new ArrayList<String>();
	ArrayList<String> etiquetasColectivo2 = new ArrayList<String>();
	ArrayList<String> etiquetasCGP = new ArrayList<String>();
	ArrayList<String> etiquetasKiosco= new ArrayList<String>();
	
	POI paradaColectivo = new ParadaColectivo(etiquetasColectivo,null);
	POI paradaColectivo2 = new ParadaColectivo(etiquetasColectivo2,null);
	POI CGP1 = new CGP(etiquetasCGP, null, null);
	LocalComercial kiosco = new LocalComercial(RadioCercania.Kiosco);
	
	@Before
	public void inicializar(){
		paradaColectivo2.agregarEtiqueta("retiro");
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
		repositorio.agregarPOI(paradaColectivo2);
	}
	
	@After
	public void vaciarRepositorio(){
		repositorio.removerPOI(paradaColectivo2);
		repositorio.removerPOI(paradaColectivo);
		repositorio.removerPOI(kiosco);
		repositorio.removerPOI(CGP1);
	}
	
	@Test
	public void buscarColectivo7(){
		Consulta consulta7 =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consulta7);		
		unUsuario.buscarPOIPorPalabra("7");
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);
	}
	
	@Test
	public void buscarColectivoARetiro(){
		Consulta consultaRetiro = new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaRetiro);
		unUsuario.buscarPOIPorPalabra("retiro");
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(),2);
	}
	
	@Test
	public void buscarPorFlores(){
		Consulta consultaFlores =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaFlores);		
		unUsuario.buscarPOIPorPalabra("flores");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorColectivo114(){
		Consulta consulta114 =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consulta114);		
		unUsuario.buscarPOIPorPalabra("114");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 0);		
	}
	
	@Test
	public void buscarPorParqueChacabuco(){
		Consulta consultaParqueChacabuco =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaParqueChacabuco);		
		unUsuario.buscarPOIPorPalabra("parque chacabuco");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorFotocopias(){
		Consulta consultaFotocopias =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaFotocopias);		
		unUsuario.buscarPOIPorPalabra("fotocopias");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorCigarrillos(){
		Consulta consultaCigarrillos =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaCigarrillos);		
		unUsuario.buscarPOIPorPalabra("cigarrillos");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorCaramelos(){
		Consulta consultaCaramelos =  new Consulta(repositorio);
		unUsuario.agregarConsulta(consultaCaramelos);		
		unUsuario.buscarPOIPorPalabra("caramelos");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 0);		
	}
	
	
}
