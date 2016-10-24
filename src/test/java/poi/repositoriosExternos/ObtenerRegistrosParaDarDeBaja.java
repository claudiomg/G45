package poi.repositoriosExternos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import poi.repositorios.RepositorioPOIsADarDeBaja;

public class ObtenerRegistrosParaDarDeBaja {
	
	RepositorioPOIsADarDeBaja repoDeBaja = RepositorioPOIsADarDeBaja.getInstance();
	
	@After
	public void vaciarRepositorio(){
		repoDeBaja.cleanRepository();
	}
	
	@Test
	public void probarJson() throws Exception{
		((RepositorioPOIsADarDeBaja)repoDeBaja).actualizarRepositorio();
		Assert.assertEquals(2, repoDeBaja.obtenerRegistros().size());
	
	}
}
