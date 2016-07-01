package poi.calculoDeCercania;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.acciones.Accion;
import poi.acciones.EstaCerca;
import poi.acciones.EstaDisponible;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;

public class CercaniaBancoTest {

	Posicion posicionSucursal = new Posicion(40.417, 3.703);
	Posicion posicionUsuario = new Posicion(40.453, 3.68);		
	Terminal unUsuario = new Terminal();
	RepositorioPOI repositorio;
	Consulta unaConsulta = new Consulta(repositorio);
	List<String> etiquetas = new ArrayList<String>();
	POI sucursal = new SucursalBanco(etiquetas, posicionSucursal); 
	LocalDate fecha;
	
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionUsuario);
		unUsuario.agregarConsulta(unaConsulta,LocalDate.of(2016, 6, 27));
	}

	@Test
	public void unaSucursalBancoEstaMuyLejos(){ //4.45 kms				
		Assert.assertFalse(unUsuario.estoyCercaDe(sucursal));
	}

	@Test
	public void unaSucursalBancoEstaCasiEnElLimiteDeLejania(){ //0.507 kms				
		posicionSucursal = new Posicion(40.453, 3.686);
		sucursal.setPosicion(posicionSucursal);
		EstaCerca estaCercaTest = new EstaCerca(sucursal,unUsuario.getPosicion());
		agregarYEjecutarAccion(sucursal,estaCercaTest);
		Assert.assertFalse(estaCercaTest.getCercania());
	}

	@Test
	public void unaSucursalBancoEstaCasiEnElLimiteDeCercania(){ //0.499 kms				
		posicionSucursal = new Posicion(40.453, 3.6859);
		sucursal.setPosicion(posicionSucursal);
		EstaCerca estaCercaTest = new EstaCerca(sucursal,unUsuario.getPosicion());
		agregarYEjecutarAccion(sucursal,estaCercaTest);
		Assert.assertTrue(unUsuario.estoyCercaDe(sucursal));
	}
	
	@Test
	public void unaSucursalBancoEstaDemasiadoCerca(){ // 0 kms	
		posicionSucursal = new Posicion(40.453, 3.68);
		sucursal.setPosicion(posicionSucursal);
		EstaCerca estaCercaTest = new EstaCerca(sucursal,unUsuario.getPosicion());
		agregarYEjecutarAccion(sucursal,estaCercaTest);
		Assert.assertTrue(unUsuario.estoyCercaDe(sucursal));
	}	
	
	
	private void agregarYEjecutarAccion(POI sucursal2,
			EstaCerca estaCercaTest) {
		sucursal2.agregarAccion(estaCercaTest);
		List<Accion> accionesBanco = sucursal2.acciones;
		for (Accion accion : accionesBanco){
			accion.ejecutarAccion();
		}
		
	}
}
