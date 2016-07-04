package poi.calculoDeCercania;

import org.junit.Assert;
import org.junit.Test;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class CercaniaLocalComercialTest {
	Direccion direccion = new Direccion();
	Posicion posicionUsuario = new Posicion(40.453, -3.68);

	@Test
	public void unaLibreriaEstaMuyLejos(){ //4.45 kms
		Posicion posicionLocal = new Posicion(40.417, -3.703);
		LocalComercial libreria = new LocalComercial("LIbreria 1",posicionLocal,direccion, RadioCercania.LibreriaEscolar);
		Assert.assertFalse(libreria.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unalibreriaEstaCasiEnElLimiteDeLejania(){ //0.507 kms				
		Posicion posicionLocal = new Posicion(40.453, -3.686);
		LocalComercial libreria = new LocalComercial("LIbreria 1",posicionLocal,direccion, RadioCercania.LibreriaEscolar);
		Assert.assertFalse(libreria.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unalibreriaEstaCasiEnElLimiteDeCercania(){ //0.49 kms				
		Posicion posicionLocal = new Posicion(40.453, -3.6858);
		LocalComercial libreria = new LocalComercial("LIbreria 1",posicionLocal,direccion, RadioCercania.LibreriaEscolar);
		Assert.assertTrue(libreria.estaCercaDe(posicionUsuario));
	}
	
	@Test
	public void unalibreriaEstaDemasiadoCerca(){ // 0 kms	
		Posicion posicionLocal = new Posicion(40.453, -3.68);
		LocalComercial libreria = new LocalComercial("LIbreria 1",posicionLocal,direccion, RadioCercania.LibreriaEscolar);
		Assert.assertTrue(libreria.estaCercaDe(posicionUsuario));
	}	
	
	@Test
	public void unKioscoEstaMuyLejos(){ //4.45 kms
		Posicion posicionLocal = new Posicion(40.417, -3.703);
		LocalComercial kiosco = new LocalComercial("Kiosco 1",posicionLocal,direccion, RadioCercania.Kiosco);
		Assert.assertFalse(kiosco.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unKioscoEstaCasiEnElLimiteDeLejania(){ //0.203 kms				
		Posicion posicionLocal = new Posicion(40.453, -3.6824);
		LocalComercial kiosco = new LocalComercial("Kiosco 1",posicionLocal,direccion, RadioCercania.Kiosco);
		Assert.assertFalse(kiosco.estaCercaDe(posicionUsuario));
	}

	@Test
	public void unKioscoEstaCasiEnElLimiteDeCercania(){ //0.194 kms				
		Posicion posicionLocal = new Posicion(40.453, -3.6823);
		LocalComercial kiosco = new LocalComercial("Kiosco 1",posicionLocal,direccion, RadioCercania.Kiosco);
		Assert.assertTrue(kiosco.estaCercaDe(posicionUsuario));
	}
	
	@Test
	public void unKioscoEstaDemasiadoCerca(){ // 0 kms
		Posicion posicionLocal = new Posicion(40.453, -3.68);
		LocalComercial kiosco = new LocalComercial("Kiosco 1",posicionLocal,direccion, RadioCercania.Kiosco);
		Assert.assertTrue(kiosco.estaCercaDe(posicionUsuario));
	}	

}
