package poi.busquedaPorTexto;
import java.time.LocalDate;
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
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioCGPExternos;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;

public class BuscarPorNombreTest {
    
	RepositorioAbstracto repo2 = RepositorioCGPExternos.getInstance();
	RepositorioAbstracto repositorio = RepositorioPOI.getInstance();
	Terminal unUsuario = new Terminal();
	
	ArrayList<String> etiquetasColectivo = new ArrayList<String>();
	ArrayList<String> etiquetasColectivo2 = new ArrayList<String>();
	ArrayList<String> etiquetasCGP = new ArrayList<String>();
	ArrayList<String> etiquetasKiosco= new ArrayList<String>();
	
	POI paradaColectivo = new ParadaColectivo(etiquetasColectivo,null);
	POI paradaColectivo2 = new ParadaColectivo(etiquetasColectivo2,null);
	POI CGP1 = new CGP(etiquetasCGP, null, null);
	LocalComercial kiosco = new LocalComercial(RadioCercania.Kiosco);
	LocalDate fechaHora;
	
	
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
		repositorio.eliminarPOI(paradaColectivo2);
		repositorio.eliminarPOI(paradaColectivo);
		repositorio.eliminarPOI(kiosco);
		repositorio.eliminarPOI(CGP1);
	}
	
	@Test
	public void buscarColectivo7(){
		Consulta consulta7 =  new Consulta(repositorio);
		consulta7.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consulta7, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("7");
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);
	}
	
	@Test
	public void buscarColectivoARetiro(){
		Consulta consultaRetiro = new Consulta(repositorio);
		consultaRetiro.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consultaRetiro, LocalDate.of(2016, 6, 27));
		unUsuario.buscarPOIPorPalabra("retiro");
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(),2);
	}
	
	@Test
	public void buscarPorFlores(){
		Consulta consultaFlores =  new Consulta(repositorio);
		consultaFlores.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consultaFlores, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("flores");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorColectivo114(){
		Consulta consulta114 =  new Consulta(repositorio);
		consulta114.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consulta114, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("114");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 0);		
	}
	
	@Test
	public void buscarPorParqueChacabuco(){
		Consulta consultaParqueChacabuco =  new Consulta(repositorio);
		consultaParqueChacabuco.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consultaParqueChacabuco, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("parque chacabuco");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorFotocopias(){
		Consulta consultaFotocopias =  new Consulta(repositorio);
		consultaFotocopias.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consultaFotocopias, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("fotocopias");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorCigarrillos(){
		Consulta consultaCigarrillos =  new Consulta(repositorio);
		consultaCigarrillos.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consultaCigarrillos, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("cigarrillos");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 1);		
	}
	
	@Test
	public void buscarPorCaramelos(){
		Consulta consultaCaramelos =  new Consulta(repositorio);
		consultaCaramelos.setRepoExterno(repo2);
		unUsuario.agregarConsulta(consultaCaramelos, LocalDate.of(2016, 6, 27));		
		unUsuario.buscarPOIPorPalabra("caramelos");		
		Assert.assertEquals(unUsuario.numeroDePoisEncontrados(), 0);		
	}
	
	
}
