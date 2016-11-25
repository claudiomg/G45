package poi.entrega6;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.Posicion;

public class ActualizarCoordenadasDePoiTest implements WithGlobalEntityManager{
	Posicion posicion = new Posicion(21.12, 12.21);
	Direccion direccion = new Direccion();
	SucursalBanco banco = new SucursalBanco("Sucursal Prueba 1", posicion, direccion);
	ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();
	
	@Before
	public void setUp(){
		Query query = entityManager().createQuery("from POI where NOMBRE_POI = :nombre ",POI.class);
		query.setParameter("nombre", "Sucursal Prueba 1");
		List<POI> pois = query.getResultList();
		// elimino todos los posibles poi con un determinado nombre
		
		for (POI poi : pois){
			entityManager().getTransaction().begin();
			entityManager().remove(poi);
			entityManager().getTransaction().commit();
		}
		direccion.setBarrio("Almagro");
		direccion.setLocalidad("Chivilcoy");
		direccion.setCalle("Guemes");
		direccion.setCodigoPostal("2222");
		
	}
	
	@Test
	public void updateLatitudeAndLongitude() {
		Query query = entityManager().createQuery("from POI where NOMBRE_POI = :nombre ",POI.class);
		query.setParameter("nombre", "Sucursal Prueba 1");
		
		feriados.agregarFeriados(LocalDateTime.of(0, 7, 9, 0, 0));
		feriados.agregarFeriados(LocalDateTime.of(0, 5, 1, 0, 0));
		banco.setFeriados(feriados);
		
		entityManager().getTransaction().begin();
		entityManager().persist(direccion);
		entityManager().persist(posicion);
		entityManager().persist(feriados);
		entityManager().persist(banco);
		entityManager().getTransaction().commit();
		
		banco.getPosicion().setLatitud(36.0);
		banco.getPosicion().setLongitud(45.0);
		
		Assert.assertTrue(query.getResultList().size()==1);
		
		Query query2 = entityManager().createNativeQuery(
				"select Posiciones.latitud, Posiciones.longitud, POIS.NOMBRE_POI "
				+ "from Posiciones "
				+ "INNER JOIN POIS "
				+ "ON Posiciones.PosicionId = POIS.posicionId "
				+ "WHERE POIS.NOMBRE_POI = 'Sucursal Prueba 1' "
				+ "AND Posiciones.latitud = 21.12"
				+ "AND Posiciones.longitud = 12.21");
		Assert.assertTrue(query2.getResultList().size()==1);
		
		entityManager().getTransaction().begin();
		entityManager().persist(posicion);
		entityManager().persist(banco);
		entityManager().getTransaction().commit();
		
		Query query3 = entityManager().createNativeQuery(
				"select Posiciones.latitud, Posiciones.longitud, POIS.NOMBRE_POI "
				+ "from Posiciones "
				+ "INNER JOIN POIS ON Posiciones.PosicionId = POIS.posicionId "
				+ "WHERE POIS.NOMBRE_POI = 'Sucursal Prueba 1' "
				+ "AND Posiciones.latitud = 36 "
				+ "AND Posiciones.longitud = 45");
		Assert.assertTrue(query3.getResultList().size()==1);
	}
}
