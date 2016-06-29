package poi.UtilidadesTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.RepositorioAdministrador;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;

public class MailSenderTest {
	
	RepositorioAbstracto repoPOI = RepositorioPOI.getInstance();
	Terminal terminal = new Terminal();
	Administrador admin = new Administrador(repoPOI);
	RepositorioAdministrador repoAdmin = RepositorioAdministrador.getInstance();
	Posicion posicionTerminal = new Posicion(40.4533, -3.681);	
	Posicion posicion1 = new Posicion(40.4534, -3.68);
	Posicion posicion2 = new Posicion(40.4535, -3.68);
	Posicion posicion3 = new Posicion(40.4536, -3.68);
	Posicion posicion4 = new Posicion(40.4537, -3.68);	
	List<String> etiquetas = new ArrayList<String>();
	POI parada1 = new ParadaColectivo(etiquetas, posicion1);
	POI parada2 = new ParadaColectivo(etiquetas, posicion2);
	POI parada3 = new ParadaColectivo(etiquetas, posicion3);
	POI parada4 = new ParadaColectivo(etiquetas, posicion4);
	
	Consulta unaConsulta = new Consulta(repoPOI);
	
	@Before
	public void inicializarEscenario(){
		admin.setMail("claudiomgerez@gmail.com");
		etiquetas.add("Palermo");
		terminal.agregarConsulta(unaConsulta, LocalDate.now());
		terminal.setPosicion(posicionTerminal);
		repoPOI.agregarPOI(parada1);
		repoPOI.agregarPOI(parada2);
		repoPOI.agregarPOI(parada3);
		repoPOI.agregarPOI(parada4);
		repoAdmin.getAdministradores().add(admin);
	}
	
	@Test
	public void mandaMailTest(){
		unaConsulta.buscarPorPalabra("Palermo");
		Assert.assertTrue(unaConsulta.getTiempoProcesamientoMaximo() == 10);
	}

}
