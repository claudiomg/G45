package poi.PersistenceTest;

import static org.junit.Assert.*;

import org.junit.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import poi.utilidades.Direccion;

public class PersistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

	private Direccion dir = new Direccion();
	
	@Before
	public void setUp(){
		/** CREAR DIRECCION PERSISTIBLE **/
		
		dir.setBarrio("Almagro");
		dir.setLocalidad("Chivilcoy");
		dir.setCalle("Guemes");
		dir.setCodigoPostal("2222");
	}
	
	@Test
	public void crearDireccionTest(){
		entityManager().persist(dir);
		assertEquals(dir.getDireccionId(), entityManager().find(Direccion.class, dir.getDireccionId() ).getDireccionId() );
		/** BORRAR **/
	}
}
