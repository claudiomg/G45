package poi.PersistenceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;

public class PersistenceMongoTest {

	private RepositorioConsultas repoConsultas;
	
	@Before
	public void setUp(){
		repoConsultas = RepositorioConsultas.getInstance();
	}
	
	@After
	public void limpiarBase(){
		repoConsultas.cleanRepository();
	}
	
	@Test
	public void persisteConsulta(){
		Consulta consulta = new Consulta();
		
		repoConsultas.agregarRegistro(consulta);
		
		assertEquals(consulta.getId(), repoConsultas.getRegistroPorId(consulta.getId()).getId() );
	}
	
}
