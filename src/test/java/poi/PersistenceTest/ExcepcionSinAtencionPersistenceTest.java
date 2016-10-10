package poi.PersistenceTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;


import poi.utilidades.ExcepcionSinAtencion;

public class ExcepcionSinAtencionPersistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	private ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();
	
	@Before
	public void setUp(){
		
		feriados.agregarFeriados(LocalDateTime.of(0, 7, 9, 0, 0));
		feriados.agregarFeriados(LocalDateTime.of(0, 5, 1, 0, 0));
		
	}
	
	@Test
	public void crearDireccionTest(){
		entityManager().persist(feriados);
		assertEquals(feriados.getFeriados().size(), entityManager().find(ExcepcionSinAtencion.class, feriados.getExcepcionSinAtencionId()).getFeriados().size() );
		/** BORRAR **/
	}

}
