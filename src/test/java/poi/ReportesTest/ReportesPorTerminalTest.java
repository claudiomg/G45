package poi.ReportesTest;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorTerminal;
import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;

public class ReportesPorTerminalTest {
	ReporteBusquedasPorTerminal reporte = new ReporteBusquedasPorTerminal();
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
		repositorio.agregarRegistro(consulta);
		reporte.dumpReport();
		Assert.assertFalse(reporte.getResults().toString().equals("[]"));
	}
	@Test
	public void cuandoHayConsultasDeMismaTerminal(){
		consulta = new Consulta(abasto, "palabra buscada 1");
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(abasto, "palabra buscada 2");
		repositorio.agregarRegistro(consulta);
		
		reporte.dumpReport();
		HashMap<String, Object> result = reporte.getResults().get(0);
		Assert.assertTrue(result.get("Cantidad").equals(2));
		Assert.assertTrue(result.get("Terminal").equals("Abasto"));
	}
	
	@Test
	public void cuandoHayConsultasDeDistintasTerminales(){
		consulta = new Consulta(abasto, "palabra buscada 1");
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(abasto, "palabra buscada 2");
		repositorio.agregarRegistro(consulta);
		
		consulta = new Consulta(caballito, "palabra buscada 3");
		repositorio.agregarRegistro(consulta);
		
		reporte.dumpReport();
		
		reporte.dumpReport();
		HashMap<String, Object> result1 = reporte.getResults().get(1);
		HashMap<String, Object> result2 = reporte.getResults().get(0);
		
		Assert.assertTrue(result1.get("Cantidad").equals(1));
		Assert.assertTrue(result1.get("Terminal").equals("Caballito"));
		
		Assert.assertTrue(result2.get("Cantidad").equals(2));
		Assert.assertTrue(result2.get("Terminal").equals("Abasto"));
	}
}
