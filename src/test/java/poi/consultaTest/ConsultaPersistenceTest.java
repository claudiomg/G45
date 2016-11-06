package poi.consultaTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Direccion;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.ExcepcionSinAtencion;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class ConsultaPersistenceTest implements WithGlobalEntityManager {
	
	Posicion posicion = new Posicion(40.417, 3.703);
	Posicion posicion1 = new Posicion(58.417, 9.703);
	Direccion direccion = new Direccion();
	Direccion direccion1 = new Direccion();
	POI kiosco = new LocalComercial("kiosco1", posicion, direccion, RadioCercania.Kiosco);
	POI parada = new ParadaColectivo("114", posicion1, direccion1);
	Terminal terminal = new Terminal("Belgrano");
	Consulta consulta = new Consulta();
	TimeRange rango = new TimeRange(LocalTime.of(10, 30, 00), LocalTime.of(22, 30, 00));
	DisponibilidadHoraria disponibilidadHoraria = new DisponibilidadHoraria();
	List<POI> poisEncontrados = new ArrayList<>();
	ExcepcionSinAtencion feriados = new ExcepcionSinAtencion();
	ExcepcionSinAtencion feriados1 = new ExcepcionSinAtencion();

	@Before
	
	public void setUp() {
		
		direccion.setBarrio("Almagro");
		direccion.setLocalidad("Chivilcoy");
		direccion.setCalle("Guemes");
		direccion.setCodigoPostal("2222");
		
		direccion1.setBarrio("Floresta");
		direccion1.setLocalidad("CABA");
		direccion1.setCalle("Segurola");
		direccion1.setCodigoPostal("1407");
				
		parada.setDireccion(direccion1);
		kiosco.setDireccion(direccion);
		
		feriados1.agregarFeriados(null);
		parada.setFeriados(feriados1);
		
		feriados.agregarFeriados(LocalDateTime.of(0, 7, 9, 0, 0));
		feriados.agregarFeriados(LocalDateTime.of(0, 5, 1, 0, 0));
		disponibilidadHoraria.agregarNuevoRango(rango);	
		
		kiosco.addDisponibilidadDeAtencion(disponibilidadHoraria);
		kiosco.setFeriados(feriados);
		
		poisEncontrados.add(kiosco);
		poisEncontrados.add(parada);
		
		consulta.setUser(terminal);
		consulta.setPoisEncontrados(poisEncontrados);
		consulta.setDuracionProceso(null);
		consulta.setCantidadEncontrada(poisEncontrados.size());
		consulta.setPalabraBuscada(null);
		consulta.setComienzoProceso(null);
		consulta.setFinProceso(null);
		
		
	}
	
	@Test
	
	public void consultaTest(){
		
		entityManager().getTransaction().begin();
		entityManager().persist(terminal);
		entityManager().persist(direccion);
		entityManager().persist(direccion1);
		entityManager().persist(posicion);
		entityManager().persist(posicion1);
		entityManager().persist(disponibilidadHoraria);
		entityManager().persist(feriados);
		entityManager().persist(feriados1);
		entityManager().persist(kiosco);
		entityManager().persist(parada);
		entityManager().persist(consulta);
		entityManager().getTransaction().commit();
		
		String nombreABuscar = "Belgrano";
		String nombre;
		
		Long idTerminal;
		
		idTerminal = entityManager()
				.createQuery("from UsuarioPOI l where l.usuario like :user", UsuarioPOI.class)
				.setParameter("user", nombreABuscar).getResultList().get(0).getId();
		
		Terminal term = new Terminal();
		
		term = entityManager()
				.createQuery("from Terminal l where l.user like :user", Terminal.class)
				.setParameter("user", idTerminal).getResultList().get(0);
		
		
		nombre = entityManager()
				.createQuery("from Consulta l where l.user = :terminalId", Consulta.class)
				.setParameter("terminalId", term).getResultList().get(0).getUser().getUsuario();
		
		Assert.assertTrue(nombreABuscar == nombre);
	}
}
