package poi.PersistenceTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import poi.utilidades.ExcepcionHorarioCambiado;
import poi.utilidades.TimeRange;

public class PersistenceTestExcepcionHorarioCambiado  extends AbstractPersistenceTest implements WithGlobalEntityManager{

	TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	ExcepcionHorarioCambiado excepcion = new ExcepcionHorarioCambiado(LocalDate.now());
	
	@Before
	public void setUp(){
		excepcion.agregarRangoCambiado(rangoInferiorDeLaSemana);
		excepcion.agregarRangoCambiado(rangoSuperiorDeLaSemana);
	}
	
	@Test
	public void crearExcepcionRangoCambiadoTest(){
		entityManager().persist(excepcion);
		assertEquals(excepcion.getExcepcionesHorarioCambiadoId(), entityManager().find(ExcepcionHorarioCambiado.class, excepcion.getExcepcionesHorarioCambiadoId() ).getExcepcionesHorarioCambiadoId() );
		/** BORRAR **/
	}

	
}
