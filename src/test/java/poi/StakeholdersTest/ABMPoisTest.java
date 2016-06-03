package poi.StakeholdersTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.Usuario;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;

public class ABMPoisTest {

	RepositorioPOI repositorio = RepositorioPOI.getInstance();
	Administrador admin = new Administrador(repositorio);
	
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
		admin.agregarPOI(CGP1);
	}
	
	@After
	public void vaciarRepositorio(){
		admin.removerPOI(CGP1);
		admin.removerPOI(kiosco);
	}
	
	@Test
	public void administradorAgregaPOI(){
		admin.agregarPOI(kiosco);
		Assert.assertTrue(repositorio.getPois().size() == 2);
	}
	
	@Test
	public void administradorBorraPOI(){
		admin.removerPOI(CGP1);
		Assert.assertTrue(repositorio.getPois().size() == 0);
	}
	
	
}
