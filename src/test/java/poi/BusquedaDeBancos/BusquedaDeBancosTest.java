package poi.BusquedaDeBancos;


import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.BusquedaDeBancos;
import poi.utilidades.Consulta;

public class BusquedaDeBancosTest {
	

	RepositorioAbstracto repositorioBancos = RepositorioBancosExternos.getInstance();
	RepositorioAbstracto repositorioPoi = RepositorioPOI.getInstance();
	Terminal unUsuario = new Terminal();
	BusquedaDeBancos busquedaBancos = new BusquedaDeBancos();
	
	SucursalBanco bancoSantander = new SucursalBanco(null,null);
	SucursalBanco bancoGalicia = new SucursalBanco(null,null);
	SucursalBanco bancoProvincia = new SucursalBanco(null,null);
	ArrayList<String> servicios1 = new ArrayList<String>();
	ArrayList<String> servicios2 = new ArrayList<String>();	
	ArrayList<String> servicios3 = new ArrayList<String>();
	
	@Before
	public void inicializar(){
		servicios1.add("cajero");
		servicios1.add("transacciones");
		servicios1.add("deposito");
		servicios2.add("prestamo");
		servicios2.add("cajero");
		servicios2.add("prestamo");
		servicios3.add("prestamo");
		servicios3.add("cajero");
		bancoSantander.setNombre("Santander");
		bancoSantander.setSucursal("Flores");
		bancoSantander.setGerente("Perez");
		bancoSantander.setServicios(servicios1);
		bancoGalicia.setNombre("Provincia");
		bancoGalicia.setServicios(servicios2);
		bancoProvincia.setNombre("Galicia");
		bancoProvincia.setServicios(servicios3);
		repositorioPoi.agregarPOI(bancoGalicia);
		repositorioPoi.agregarPOI(bancoProvincia);
		repositorioBancos.agregarPOI(bancoSantander);
		busquedaBancos.setRepositorioBancoExterno(repositorioBancos);
		busquedaBancos.setRepositorioDePois(repositorioPoi);
	}
	
	@After
	public void vaciarRepositorio(){
		
			repositorioPoi.eliminarPOI(bancoGalicia);
			repositorioPoi.eliminarPOI(bancoProvincia);
			repositorioBancos.eliminarPOI(bancoSantander);			
}
		
		@Test
		public void buscarBancosPorNombreSantanderYServicioCajero(){
			Consulta consulta1 = new Consulta(null);
			consulta1.setBusquedaDeBancos(busquedaBancos);
			unUsuario.agregarConsulta(consulta1,null);
			System.out.println(busquedaBancos.getRepositorioBancoExterno().pois.size());
			System.out.println(busquedaBancos.getRepositorioDePois().pois.size());
			Assert.assertEquals(unUsuario.buscarBancos("Santander", "cajero").size(),2);			
		}
		
		@Test
		public void buscarBancosPorNombreGaliciaYCajero(){
			Consulta consulta2 = new Consulta(null);
			consulta2.setBusquedaDeBancos(busquedaBancos);
			unUsuario.agregarConsulta(consulta2,null);
			Assert.assertEquals(unUsuario.buscarBancos("Galicia", "cajero").size(),2);
		}
		
		@Test
		public void buscarBancosPorNombreSantanderYPrestamo(){
			Consulta consulta3 = new Consulta(null);
			consulta3.setBusquedaDeBancos(busquedaBancos);
			unUsuario.agregarConsulta(consulta3,null);
			Assert.assertEquals(unUsuario.buscarBancos("Santander", "prestamo").size(),0);			
		}
}

