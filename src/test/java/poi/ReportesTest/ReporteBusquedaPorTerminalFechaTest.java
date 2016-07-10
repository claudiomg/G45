package poi.ReportesTest;

import java.time.LocalDateTime;
import java.util.HashMap;

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
		
		HashMap<String, Object> result = reporte.getResults().get(0);
		
		Assert.assertTrue(result.get("Cantidad").equals("2"));
		Assert.assertTrue(result.get("Terminal").equals("Abasto"));
		Assert.assertTrue(result.get("Fecha").equals("2016-06-26"));
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
		
		HashMap<String, Object> result1 = null;
		for ( HashMap<String, Object> r : reporte.getResults()){
			boolean bool = r.get("Terminal").equals("Abasto");
			if (bool){
				result1 = r;
				break;
			}
		}
		Assert.assertTrue(result1.get("Cantidad").equals("2"));
		Assert.assertTrue(result1.get("Terminal").equals("Abasto"));
		Assert.assertTrue(result1.get("Fecha").equals("2016-06-26"));
		
		HashMap<String, Object> result2 = null;
		for ( HashMap<String, Object> r : reporte.getResults()){
			boolean bool = r.get("Terminal").equals("Caballito");
			if (bool){
				result2 = r;
				break;
			}
		}
		Assert.assertFalse(result1 == null);
		Assert.assertFalse(result2 == null);
		Assert.assertTrue(result2.get("Cantidad").equals("1"));
		Assert.assertTrue(result2.get("Terminal").equals("Caballito"));
		Assert.assertTrue(result2.get("Fecha").equals("2016-06-01"));
	}
}
