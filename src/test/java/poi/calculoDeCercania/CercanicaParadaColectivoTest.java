package poi.calculoDeCercania;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.POI;
import poi.ParadaColectivo;
import poi.RepositorioPOI;
import usuario.Consulta;
import usuario.Posicion;
import usuario.Usuario;

public class CercanicaParadaColectivoTest {

	Posicion posicionParada = new Posicion(40.417, -3.703);
	Posicion posicionUsuario = new Posicion(40.453, -3.68);		
	RepositorioPOI repositorio;
	Consulta unaConsulta = new Consulta(repositorio);
	Usuario unUsuario = new Usuario();
	List<String> etiquetas = new ArrayList<String>();
	POI parada = new ParadaColectivo(etiquetas, posicionParada); 
	
	@Before
	public void inicializarEscenario(){
		unUsuario.setPosicion(posicionUsuario);
		unUsuario.agregarConsulta(unaConsulta);
	}

	@Test
	public void unaParadaEstaMuyLejos(){ //4.45 kms				
		Assert.assertFalse(unUsuario.estoyCercaDe(parada));
	}

	@Test
	public void unaParadaEstaCasiEnElLimiteDeLejania(){ //0.109 kms				
		posicionParada = new Posicion(40.453, -3.6813);
		parada.setPosicion(posicionParada);
		Assert.assertFalse(unUsuario.estoyCercaDe(parada));
	}

	@Test
	public void unaParadaEstaCasiEnElLimiteDeCercania(){ //0.093 kms				
		posicionParada = new Posicion(40.453, -3.6811);
		parada.setPosicion(posicionParada);
		Assert.assertTrue(unUsuario.estoyCercaDe(parada));
	}
	
	@Test
	public void unaParadaEstaDemasiadoCerca(){ // 0 kms	
		posicionParada = new Posicion(40.453, -3.68);
		parada.setPosicion(posicionParada);
		Assert.assertTrue(unUsuario.estoyCercaDe(parada));
	}	
}
