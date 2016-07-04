package poi.busquedaPorTexto;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class BusquedaDePoiPorPalabraTest {
	
	@Test
	public void cuandoBuscoBancoPorNombreCompletoLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Sucursal Belgrano", posicion , direccion);
		Assert.assertTrue(unBanco.matches("Sucursal Belgrano"));
	}
	
	@Test
	public void cuandoBuscoBancoPorNombreImcompletoLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Sucursal Belgrano", posicion , direccion);
		Assert.assertTrue(unBanco.matches("Sucur"));
	}
	
	@Test
	public void cuandoBuscoBancoPorNombreCapitalizadoLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Sucursal Belgrano", posicion , direccion);
		Assert.assertTrue(unBanco.matches("SUCUR"));
	}
	
	@Test
	public void cuandoBuscoBancoPorServicioCompletoLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Sucursal", posicion , direccion);
		unBanco.agregarServicio("Deposito");
		Assert.assertTrue(unBanco.matches("Deposito"));
	}
	
	@Test
	public void cuandoBuscoBancoPorServicioInconpletoLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Sucursal", posicion , direccion);
		unBanco.agregarServicio("Deposito");
		Assert.assertTrue(unBanco.matches("Depo"));
	}
	
	@Test
	public void cuandoBuscoBancoPorNombreIncorrectoNoMachea(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Belgrano", posicion , direccion);
		Assert.assertFalse(unBanco.matches("Lugano"));
	}
	@Test
	public void cuandoBuscoBancoPorDireccionLaEncuentro(){
		Direccion direccion = new Direccion();
		direccion.setCalle("Rivadavia");
		Posicion posicion = new Posicion(0.0, 0.0);
		SucursalBanco unBanco =  new SucursalBanco("Caballito", posicion , direccion);
		Assert.assertTrue(unBanco.matches("Riva"));
	}
	
	@Test
	public void cuandoBuscoParadaColectivoPorNumeroLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		ParadaColectivo unBanco =  new ParadaColectivo("47", posicion , direccion);
		Assert.assertTrue(unBanco.matches("47"));
	}
	@Test
	public void cuandoBuscoParadaColectivoPorNumeroIncorrectoNoLoEncuentro(){
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(0.0, 0.0);
		ParadaColectivo unBanco =  new ParadaColectivo("47", posicion , direccion);
		Assert.assertFalse(unBanco.matches("57"));
	}
}
