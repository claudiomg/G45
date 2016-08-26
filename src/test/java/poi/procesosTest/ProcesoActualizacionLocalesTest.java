package poi.procesosTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.procesos.ProcesoActualizacionLocales;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;

public class ProcesoActualizacionLocalesTest {

	LocalComercial unKiosco = new LocalComercial("Kiosco1",null, null, null);
	RepositorioAbstractoPOI repositorioLocal = RepositorioPOI.getInstance();		
	ProcesoActualizacionLocales proceso = new ProcesoActualizacionLocales();

	@After
	public void vaciarRepositorio(){
		repositorioLocal.cleanRepository();
	}

	@Test
	public void existeLocalCambiaEtiquetasTest(){
		repositorioLocal.agregarRegistro(unKiosco);		
		proceso.correrProceso();
		Assert.assertTrue(unKiosco.getPalabrasClave().size()==4);
	}

	@Test
	public void noExisteLocalTest(){
		LocalComercial unAlmacen = new LocalComercial("Almacen1",null, null, null);
		repositorioLocal.agregarRegistro(unAlmacen);		
		proceso.correrProceso();
		Assert.assertTrue(repositorioLocal.getRegistros().get(1).getPalabrasClave().size()==4);
	}

}
