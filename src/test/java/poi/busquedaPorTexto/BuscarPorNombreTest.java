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
	Consulta consulta = new Consulta(usuario,"banco");
	RepositorioAbstracto repositorioLocal = RepositorioPOI.getInstance();
	RepositorioAbstracto repositorioExternoBancos = RepositorioBancosExternos.getInstance();
	RepositorioAbstracto repositorioExternoCGP = RepositorioCGPExternos.getInstance();
	PoiFinder finder = new PoiFinder();
	
	
	public void inicializar(){
		finder.initializeRepositories();
		finder.initializeFilters();
		repositorioLocal.limpiarPOIs();
		repositorioExternoBancos.limpiarPOIs();
		repositorioExternoCGP.limpiarPOIs();
	}
	
	@Test
	public void obtengoUnBancoDelRepositorioLocal(){
		this.inicializar();
		this.crearBancoEnRepositorioLocal();
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
		this.inicializar();
		this.crearBancoEnRepositorioLocal();
		this.crearBancoEnRepositorioExterno();
		String palabra = "banco";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addRepository(repositorioExternoBancos);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		System.out.println(finder.getResults().size());
		Assert.assertEquals(finder.getResults().size(), 2);
	}
	
	private void crearBancoEnRepositorioExterno() {
		SucursalBanco banco = new SucursalBanco("Caballito", posicion, direccion);
		repositorioExternoBancos.agregarPOI(banco);
	}

	private void crearBancoEnRepositorioLocal() {
		SucursalBanco banco = new SucursalBanco("Belgrano", posicion, direccion);
		repositorioLocal.agregarPOI(banco);
	}
}
