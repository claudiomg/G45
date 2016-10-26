package poi.entrega6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.utilidades.Direccion;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.Posicion;

public class PoiNoEncontradoTest implements WithGlobalEntityManager{

	/*
	Posicion posicion = new Posicion(40.417, 3.703);
	Direccion direccion = new Direccion();
	LocalComercial kiosco = new LocalComercial("kiosco1",posicion,direccion,RadioCercania.Kiosco);
	
	@Before
	public void setUp(){
		
		direccion.setBarrio("Almagro");
		direccion.setLocalidad("Chivilcoy");
		direccion.setCalle("Guemes");
		direccion.setCodigoPostal("2222");
		
		kiosco.setDireccion(direccion);
	}
	*/
	@Test
	public void poiNoEncontrado(){
		
		Posicion posicion = new Posicion(40.417, 3.703);
		Direccion direccion = new Direccion();
		LocalComercial kiosco = new LocalComercial("kiosco1",posicion,direccion,RadioCercania.Kiosco);
		ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();

			
			direccion.setBarrio("Almagro");
			direccion.setLocalidad("Chivilcoy");
			direccion.setCalle("Guemes");
			direccion.setCodigoPostal("2222");
			
			kiosco.setDireccion(direccion);
			kiosco.setFeriados(feriados);
			
		//Crea y persiste POI
		entityManager().getTransaction().begin();
		entityManager().persist(kiosco);
		entityManager().getTransaction().commit();
		
		//Recupera POI
		String nombreABuscar = "kiosco1";
		Long idRecuperado;
		
		idRecuperado = entityManager() 
		.createQuery("from LocalComercial l where l.nombre like :nombre", LocalComercial.class) 
		.setParameter("nombre", "%" + nombreABuscar + "%")
		.getSingleResult()
		.getId();
		
		//Elimina POI
		entityManager().getTransaction().begin();
		entityManager().remove(kiosco);
		entityManager().getTransaction().commit();
		
		//Busca POI eliminado
		
		
		Assert.assertTrue( entityManager().createQuery("from LocalComercial l where l.PoiId = :id", LocalComercial.class).setParameter("id", idRecuperado).getSingleResult() == null);
		
		
		
		
		
	}
	
}
