package poi.calculoDeCercania;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class CercanicaParadaColectivoTest {
	Direccion direccion = new Direccion();
	Posicion posicionUsuario = new Posicion(40.453, -3.68);
	
	@Test
	public void unaParadaEstaMuyLejos(){ //4.45 kms
		Posicion posicionParada = new Posicion(40.417, -3.703);
		POI parada = new ParadaColectivo("parada1", posicionParada,direccion); 
		Assert.assertFalse(parada.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unaParadaEstaCasiEnElLimiteDeLejania(){ //0.109 kms				
		Posicion posicionParada = new Posicion(40.453, -3.6813);
		POI parada = new ParadaColectivo("parada1", posicionParada,direccion); 
		Assert.assertFalse(parada.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unaParadaEstaCasiEnElLimiteDeCercania(){ //0.093 kms				
		Posicion posicionParada = new Posicion(40.453, -3.6811);
		POI parada = new ParadaColectivo("parada1", posicionParada,direccion); 
		Assert.assertTrue(parada.estaCercaDe(posicionUsuario));
	}
	
	@Test
	public void unaParadaEstaDemasiadoCerca(){ // 0 kms	
		Posicion posicionParada = new Posicion(40.453, -3.68);
		POI parada = new ParadaColectivo("parada1", posicionParada,direccion); 
		Assert.assertTrue(parada.estaCercaDe(posicionUsuario));
	}	
}
