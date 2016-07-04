package poi.calculoDeCercania;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class CercaniaBancoTest {

	Direccion direccion = new Direccion();
	Posicion posicionUsuario = new Posicion(40.453, 3.68);

	@Test
	public void unaSucursalBancoEstaMuyLejos(){ //4.45 kms
		Posicion posicionSucursal = new Posicion(40.417, 3.703);
		POI sucursal = new SucursalBanco("sucursal1", posicionSucursal,direccion); 
		Assert.assertFalse(sucursal.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unaSucursalBancoEstaCasiEnElLimiteDeLejania(){ //0.507 kms				
		Posicion posicionSucursal = new Posicion(40.453, 3.686);
		POI sucursal = new SucursalBanco("sucursal1", posicionSucursal,direccion); 
		Assert.assertFalse(sucursal.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unaSucursalBancoEstaCasiEnElLimiteDeCercania(){ //0.499 kms				
		Posicion posicionSucursal = new Posicion(40.453, 3.6859);
		POI sucursal = new SucursalBanco("sucursal1", posicionSucursal,direccion); 
		Assert.assertTrue(sucursal.estaCercaDe(posicionUsuario));
	}
	
	@Test
	public void unaSucursalBancoEstaDemasiadoCerca(){ // 0 kms	
		Posicion posicionSucursal = new Posicion(40.453, 3.68);
		POI sucursal = new SucursalBanco("sucursal1", posicionSucursal,direccion); 
		Assert.assertTrue(sucursal.estaCercaDe(posicionUsuario));
	}	

}
