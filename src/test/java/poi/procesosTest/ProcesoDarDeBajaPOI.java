package poi.procesosTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.DarDeBaja;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.usuario.Administrador;
import poi.procesos.ProcesoDarDeBajaPois;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;
import poi.repositorios.RepositorioPOIsADarDeBaja;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class ProcesoDarDeBajaPOI {
	
	Direccion direccion = new Direccion();
	Posicion posicion = new Posicion(0.0, 0.0);
	ProcesoDarDeBajaPois darDeBaja = new ProcesoDarDeBajaPois();
	PoiFinder poiFinder = new PoiFinder();
	RepositorioPOIsADarDeBaja repoDeBaja = RepositorioPOIsADarDeBaja.getInstance();
    RepositorioPOI repoDePoi = RepositorioPOI.getInstance();
    Administrador admin = new Administrador("chris");
    List<String> palabrasClaves = new ArrayList<String>();
	List<String> palabrasClaves2 = new ArrayList<String>();
	POI colectivo = new ParadaColectivo("Estacion Chacarita", posicion, direccion);
	POI colectivo2 = new ParadaColectivo("Estacion Castelar", posicion, direccion);
	
    
	
	
	@Test
	public void DarDeBajaPOI() throws Exception{
      darDeBaja.setAdmin(admin);
      darDeBaja.setRepoDeBaja(repoDeBaja);
      darDeBaja.setPoiFinder(poiFinder);
      palabrasClaves.add("123");
      palabrasClaves2.add("122");
      colectivo.setPalabrasClave(palabrasClaves);
      repoDePoi.agregarRegistro(colectivo);
      repoDePoi.agregarRegistro(colectivo2);
      colectivo2.setPalabrasClave(palabrasClaves2);
      admin.setRepositorio(repoDePoi);
      poiFinder.addRepository(repoDePoi);
      darDeBaja.correrProceso();
	  Assert.assertTrue(admin.getRepositorio().getRegistros().size() == 1);
		
	}
	

}
