package poi.DisponiblidadTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import poi.acciones.Accion;
import poi.acciones.EstaDisponible;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.utilidades.Posicion;

public class DisponiblidadParadaColectivoTest {
	
	ArrayList<String> arrayEtiquetas = new ArrayList<String>();
	Posicion unaPosicion = new Posicion(40.417, -3.703);
	ParadaColectivo unaParadaDeColectivo = new ParadaColectivo(arrayEtiquetas,unaPosicion);
	LocalDateTime unaHora;
	
	@Test
	public void estaDisponibleDeMadrugada(){
		unaHora = LocalDateTime.of(2016, 06, 25, 3, 01);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	@Test
	public void estaDisponibleALaManiana(){
		unaHora = LocalDateTime.of(2016, 06, 25, 9, 25);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleALaTarde(){
		unaHora = LocalDateTime.of(2016, 06, 25, 16, 35);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleALaNoche(){
		unaHora = LocalDateTime.of(2016, 06, 25, 20, 41);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleUnSabado(){
		unaHora = LocalDateTime.of(2016, 04, 23, 10, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleUnDiaDeSemana(){
		unaHora = LocalDateTime.of(2016, 04, 17, 11, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	@Test
	public void estaDisponibleUnDiaFeriado(){
		unaHora = LocalDateTime.of(2016, 01, 01, 10, 00);
		EstaDisponible estaDisponibleTest = new EstaDisponible(unaParadaDeColectivo,unaHora);
		agregarYEjecutarAccion(unaParadaDeColectivo,estaDisponibleTest);
		Assert.assertTrue( estaDisponibleTest.getDisponibilidad());
	}
	
	private void agregarYEjecutarAccion(ParadaColectivo unaParadaDeColectivo,
			EstaDisponible estaDisponibleTest) {
		unaParadaDeColectivo.agregarAccion(estaDisponibleTest);
		List<Accion> accionesBanco = unaParadaDeColectivo.acciones;
 		for (Accion accion : accionesBanco){
			accion.ejecutarAccion();
		}
		
	}
	
}
