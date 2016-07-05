package poi.ReportesTest;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorFechaTerminal;
import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;

public class ReporteBusquedaPorTerminalFechaTest {
	ReporteBusquedasPorFechaTerminal reporte = new ReporteBusquedasPorFechaTerminal();
	RepositorioConsultas repositorio = RepositorioConsultas.getInstance();
	Terminal abasto = new Terminal("Abasto");
	Terminal caballito = new Terminal("Caballito");
	Consulta consulta;
	
	@Before
	public void inicializarRepositorios(){
		repositorio.cleanRepository();
	}
	
	@Test
	public void cuandoNoHayConsultasDevuelveVacio(){
		reporte.dumpReport();
		Assert.assertTrue(reporte.getResults().toString().equals("[]"));
	}
	
	@Test
	public void cuandoHayConsultasNoDevuelveVacio(){
		consulta = new Consulta(abasto, "palabra buscada");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);
		reporte.dumpReport();
		Assert.assertFalse(reporte.getResults().toString().equals("[]"));
	}
	@Test
	public void cuandoHayConsultasDeMismaTerminal(){
		consulta = new Consulta(abasto, "palabra buscada 1");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(abasto, "palabra buscada 2");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);

		reporte.dumpReport();
		Assert.assertTrue(reporte.getResults().get(0).toString().equals("{\"Fecha\":\"2016-06-26\",\"Cantidad\":\"2\",\"Terminal\":\"Abasto\"}"));
	}
	
	@Test
	public void cuandoHayConsultasDeDistintasTerminales(){
		consulta = new Consulta(abasto, "palabra buscada 1");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(abasto, "palabra buscada 2");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(caballito, "palabra buscada 3");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 1, 15, 22));
		repositorio.agregarRegistro(consulta);
		
		reporte.dumpReport();
		Assert.assertTrue(reporte.getResults().get(1).toString().equals("{\"Fecha\":\"2016-06-26\",\"Cantidad\":\"2\",\"Terminal\":\"Abasto\"}"));
		Assert.assertTrue(reporte.getResults().get(0).toString().equals("{\"Fecha\":\"2016-06-01\",\"Cantidad\":\"1\",\"Terminal\":\"Caballito\"}"));
	}
}
