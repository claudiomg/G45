package poi.ReportesTest;

import java.time.LocalDateTime;
import java.util.HashMap;

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
		
		HashMap<String, Object> result = reporte.getResults().get(0);
		Assert.assertTrue(result.get("Cantidad").equals(2));
		Assert.assertTrue(result.get("Fecha").equals("2016-06-26"));
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
		
		HashMap<String, Object> result1 = reporte.getResults().get(0);
		HashMap<String, Object> result2 = reporte.getResults().get(1);
		Assert.assertTrue(result2.get("Cantidad").equals(1));
		Assert.assertTrue(result2.get("Fecha").equals("2016-06-01"));
		Assert.assertTrue(result1.get("Cantidad").equals(2));
		Assert.assertTrue(result1.get("Fecha").equals("2016-06-26"));
	}
	
}
