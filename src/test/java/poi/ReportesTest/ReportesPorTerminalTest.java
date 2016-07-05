package poi.ReportesTest;

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
		Assert.assertTrue(reporte.getResults().get(0).toString().equals("{\"Cantidad\":\"2\",\"Terminal\":\"Abasto\"}"));
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
		Assert.assertTrue(reporte.getResults().get(1).toString().equals("{\"Cantidad\":\"2\",\"Terminal\":\"Abasto\"}"));
		Assert.assertTrue(reporte.getResults().get(0).toString().equals("{\"Cantidad\":\"1\",\"Terminal\":\"Caballito\"}"));
	}
}
