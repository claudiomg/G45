package poi.calculoDeCercania;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.Kiosco;
import poi.LibreriaEscolar;
import poi.LocalComercial;
import poi.RepositorioPOI;
import usuario.Consulta;
import usuario.Posicion;
import usuario.Usuario;

public class CercaniaLocalComercialTest {

	Posicion posicionLocal = new Posicion(40.417, -3.703);
	Posicion posicionUsuario = new Posicion(40.453, -3.68);		
	Usuario unUsuario = new Usuario();
	RepositorioPOI repositorio;
	Consulta unaConsulta = new Consulta(repositorio);
	List<String> etiquetas = new ArrayList<String>();
	LocalComercial kiosco = new LocalComercial(new Kiosco());
	LocalComercial libreria = new LocalComercial(new LibreriaEscolar());
	
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionUsuario);
		unUsuario.agregarConsulta(unaConsulta);
	}

	@Test
	public void unaLibreriaEstaMuyLejos(){ //4.45 kms
		libreria.setPosicion(posicionLocal);
		Assert.assertFalse(unUsuario.estoyCercaDe(libreria));
	}

	@Test
	public void unalibreriaEstaCasiEnElLimiteDeLejania(){ //0.507 kms				
		posicionLocal = new Posicion(40.453, -3.686);
		libreria.setPosicion(posicionLocal);
		Assert.assertFalse(unUsuario.estoyCercaDe(libreria));
	}

	@Test
	public void unalibreriaEstaCasiEnElLimiteDeCercania(){ //0.49 kms				
		posicionLocal = new Posicion(40.453, -3.6858);
		libreria.setPosicion(posicionLocal);
		Assert.assertTrue(unUsuario.estoyCercaDe(libreria));
	}
	
	@Test
	public void unalibreriaEstaDemasiadoCerca(){ // 0 kms	
		posicionLocal = new Posicion(40.453, -3.68);
		libreria.setPosicion(posicionLocal);
		Assert.assertTrue(unUsuario.estoyCercaDe(libreria));
	}	
	
	@Test
	public void unKioscoEstaMuyLejos(){ //4.45 kms
		kiosco.setPosicion(posicionLocal);
		Assert.assertFalse(unUsuario.estoyCercaDe(kiosco));
	}

	@Test
	public void unKioscoEstaCasiEnElLimiteDeLejania(){ //0.203 kms				
		posicionLocal = new Posicion(40.453, -3.6824);
		kiosco.setPosicion(posicionLocal);
		Assert.assertFalse(unUsuario.estoyCercaDe(kiosco));
	}

	@Test
	public void unKioscoEstaCasiEnElLimiteDeCercania(){ //0.194 kms				
		posicionLocal = new Posicion(40.453, -3.6823);
		kiosco.setPosicion(posicionLocal);
		Assert.assertTrue(unUsuario.estoyCercaDe(kiosco));
	}
	
	@Test
	public void unKioscoEstaDemasiadoCerca(){ // 0 kms	
		posicionLocal = new Posicion(40.453, -3.68);
		kiosco.setPosicion(posicionLocal);
		Assert.assertTrue(unUsuario.estoyCercaDe(kiosco));
	}	

}
