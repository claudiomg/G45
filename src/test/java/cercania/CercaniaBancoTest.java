package cercania;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.POI;
import poi.SucursalBanco;
import usuario.Posicion;
import usuario.Usuario;

public class CercaniaBancoTest {

	Posicion posicionSucursal = new Posicion(40.417, 3.703);
	Posicion posicionUsuario = new Posicion(40.453, 3.68);		
	Usuario unUsuario = new Usuario();
	List<String> etiquetas = new ArrayList<String>();
	POI sucursal = new SucursalBanco(etiquetas, posicionSucursal); 
	
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionUsuario);
	}

	@Test
	public void unaSucursalBancoEstaMuyLejos(){ //4.45 kms				
		Assert.assertFalse(unUsuario.estoyCercaDe(sucursal));
	}

	@Test
	public void unaSucursalBancoEstaCasiEnElLimiteDeLejania(){ //0.507 kms				
		posicionSucursal = new Posicion(40.453, 3.686);
		sucursal.setPosicion(posicionSucursal);
		Assert.assertFalse(unUsuario.estoyCercaDe(sucursal));
	}

	@Test
	public void unaSucursalBancoEstaCasiEnElLimiteDeCercania(){ //0.499 kms				
		posicionSucursal = new Posicion(40.453, 3.6859);
		sucursal.setPosicion(posicionSucursal);
		Assert.assertTrue(unUsuario.estoyCercaDe(sucursal));
	}
	
	@Test
	public void unaSucursalBancoEstaDemasiadoCerca(){ // 0 kms	
		posicionSucursal = new Posicion(40.453, 3.68);
		sucursal.setPosicion(posicionSucursal);
		Assert.assertTrue(unUsuario.estoyCercaDe(sucursal));
	}	
}
