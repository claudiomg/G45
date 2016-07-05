package poi.ReportesTest;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorFecha;
import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;

public class ReportesPorFechaTest {
	ReporteBusquedasPorFecha reporte = new ReporteBusquedasPorFecha();
	RepositorioConsultas repositorio = RepositorioConsultas.getInstance();
	Terminal usuario = new Terminal("Abasto");
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
		consulta = new Consulta(usuario, "palabra buscada");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 11, 00));
		repositorio.agregarRegistro(consulta);
		reporte.dumpReport();
		Assert.assertFalse(reporte.getResults().toString().equals("[]"));
	}
	@Test
	public void cuandoHayConsultasDeMismaFechaAcumula(){
		consulta = new Consulta(usuario, "palabra buscada 1");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 11, 00));
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(usuario, "palabra buscada 2");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);
		
		reporte.dumpReport();
		Assert.assertTrue(reporte.getResults().get(0).toString().equals("{\"Fecha\":\"2016-06-26\",\"Cantidad\":\"2\"}"));
	}
	
	@Test
	public void cuandoHayConsultasDeDistintaFechaLasSeparaCorrectamente(){
		consulta = new Consulta(usuario, "palabra buscada 1");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 11, 00));
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(usuario, "palabra buscada 2");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 26, 8, 00));
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(usuario, "palabra buscada 3");
		consulta.setComienzoProceso(LocalDateTime.of(2016, 06, 1, 15, 22));
		repositorio.agregarRegistro(consulta);
		
		reporte.dumpReport();
		Assert.assertTrue(reporte.getResults().get(0).toString().equals("{\"Fecha\":\"2016-06-26\",\"Cantidad\":\"2\"}"));
		Assert.assertTrue(reporte.getResults().get(1).toString().equals("{\"Fecha\":\"2016-06-01\",\"Cantidad\":\"1\"}"));
	}
	
}
