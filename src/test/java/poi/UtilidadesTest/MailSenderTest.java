package poi.UtilidadesTest;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.finders.FilterByProximity;
import poi.finders.FilterByTag;
import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;
import poi.repositorios.RepositorioUsuarios;
import poi.utilidades.Consulta;
import poi.utilidades.Direccion;
import poi.utilidades.PoiSystemConfiguration;
import poi.utilidades.Posicion;

public class MailSenderTest {
	RepositorioAbstractoPOI repoPOI = RepositorioPOI.getInstance();
	RepositorioUsuarios repoAdmin = RepositorioUsuarios.getInstance();
	Terminal terminal = new Terminal("Abasto");
	Administrador admin = new Administrador("admin1");

	@Before
	public void cargaUsuarios(){
		repoAdmin.agregarRegistro(terminal);
		repoAdmin.agregarRegistro(admin);
	}
	
	@Test
	public void mandaMailTest(){
		//config basicas de admin
		admin.setMail("mujica.juancarlos@gmail.com");
		repoAdmin.getAdministradores().add(admin);
		Duration duracionDefecto = Duration.ofSeconds(10);
		//config de repo
		Direccion direccion = new Direccion();
		Posicion posicionTerminal = new Posicion(40.4533, -3.681);	
		Posicion posicion1 = new Posicion(40.4534, -3.68);
		Posicion posicion2 = new Posicion(40.4535, -3.68);
		Posicion posicion3 = new Posicion(40.4536, -3.68);
		Posicion posicion4 = new Posicion(40.4537, -3.68);	
		repoPOI.agregarRegistro(new ParadaColectivo("parada1", posicion1,direccion));
		repoPOI.agregarRegistro(new ParadaColectivo("parada2", posicion2,direccion));
		repoPOI.agregarRegistro(new ParadaColectivo("parada3", posicion3,direccion));
		repoPOI.agregarRegistro(new ParadaColectivo("parada4", posicion4,direccion));
		//creo el finder
		PoiFinder finder = new PoiFinder();
		String palabra = "Palermo";
		Consulta consulta = new Consulta(terminal,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repoPOI);
		finder.addFilter(new FilterByTag(palabra));
		finder.addFilter(new FilterByProximity(posicionTerminal));
		finder.search();
		
		Assert.assertTrue(PoiSystemConfiguration.getInstance().getTiempoProcesamientoMaximo().equals(duracionDefecto) );
	}

}
