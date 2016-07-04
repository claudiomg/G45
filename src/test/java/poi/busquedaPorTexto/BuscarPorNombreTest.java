package poi.busquedaPorTexto;

import org.junit.Assert;
import org.junit.Test;

import poi.finders.FilterByTag;
import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioCGPExternos;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class BuscarPorNombreTest {
    
	Direccion direccion = new Direccion();
	Posicion posicion = new Posicion(0.0, 0.0);
	Terminal usuario = new Terminal();
	RepositorioAbstracto repositorioLocal = RepositorioPOI.getInstance();
	RepositorioAbstracto repositorioExternoBancos = RepositorioBancosExternos.getInstance();
	RepositorioAbstracto repositorioExternoCGP = RepositorioCGPExternos.getInstance();
	
	@Test
	public void obtengoUnBancoDelRepositorioLocal(){
		repositorioLocal.limpiarPOIs();
		repositorioLocal.agregarPOI(new SucursalBanco("Belgrano", posicion, direccion));
		PoiFinder finder = new PoiFinder();
		String palabra = "banco";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 1);
	}
	
	@Test
	public void obtengoDosBancosDelRepositorioLocalyExterno(){
		repositorioExternoBancos.limpiarPOIs();
		repositorioLocal.limpiarPOIs();
		repositorioExternoBancos.agregarPOI(new SucursalBanco("Caballito", posicion, direccion));
		repositorioLocal.agregarPOI(new SucursalBanco("Belgrano", posicion, direccion));
		PoiFinder finder = new PoiFinder();
		String palabra = "banco";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		//finder.addRepository(repositorioExternoBancos);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		System.out.println(repositorioLocal.getClass().getName());
		System.out.println(repositorioExternoBancos.getClass().getName());
		Assert.assertEquals(repositorioLocal.getPois().size(), 2);
		Assert.assertEquals(repositorioExternoBancos.getPois().size(), 2);
		//Assert.assertEquals(finder.getRepositories().size(), 1);
		//Assert.assertEquals(finder.getResults().size(), 2);
	}
}
