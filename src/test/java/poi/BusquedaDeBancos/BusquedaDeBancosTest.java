package poi.BusquedaDeBancos;


import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Usuario;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.BusquedaDeBancos;

public class BusquedaDeBancosTest {
	

	RepositorioBancosExternos repositorioBancos;
	RepositorioPOI repositorioPoi;
	Usuario unUsuario = new Usuario();
	BusquedaDeBancos busquedaBancos = new BusquedaDeBancos();
	
	SucursalBanco bancoSantander = new SucursalBanco(null,null);
	SucursalBanco bancoGalicia = new SucursalBanco(null,null);
	SucursalBanco bancoProvincia = new SucursalBanco(null,null);
	List<String> servicios1;
	List<String> servicios2;	
	List<String> servicios3;
	
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
		
		bancoGalicia.setNombre("Galicia");
		bancoGalicia.setServicios(servicios2);
		
		bancoProvincia.setNombre("Provincia");
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
		
	
		
	}
	
	
	
	

}
