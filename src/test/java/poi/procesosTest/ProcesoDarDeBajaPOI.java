package poi.procesosTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.usuario.Administrador;
import poi.procesos.ProcesoDarDeBajaPois;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class ProcesoDarDeBajaPOI {
	
	Direccion direccion = new Direccion();
	Posicion posicion = new Posicion(0.0, 0.0);
	ProcesoDarDeBajaPois darDeBaja = new ProcesoDarDeBajaPois();
	PoiFinder poiFinder = new PoiFinder();
    RepositorioAbstractoPOI repoDePoi = RepositorioPOI.getInstance();
    Administrador admin = new Administrador("chris");
    List<String> palabrasClaves = new ArrayList<String>();
	List<String> palabrasClaves2 = new ArrayList<String>();
	POI colectivo = new ParadaColectivo("Estacion Chacarita", posicion, direccion);
	POI colectivo2 = new ParadaColectivo("Estacion Castelar", posicion, direccion);
    
	
	
	@Test
	public void DarDeBajaPOI(){
			
			palabrasClaves.add("123");
			palabrasClaves2.add("122");

			colectivo.setPalabrasClave(palabrasClaves);
			colectivo2.setPalabrasClave(palabrasClaves2);

			repoDePoi.agregarRegistro(colectivo);
			repoDePoi.agregarRegistro(colectivo2);
			
			admin.setRepositorio((RepositorioPOI)repoDePoi);
			darDeBaja.setAdmin(admin);
			
			poiFinder.addRepository(repoDePoi);
			darDeBaja.setPoiFinder(poiFinder);
			darDeBaja.correrProceso();
			Assert.assertTrue(admin.getRepositorio().getRegistros().size() == 0);
		
	}
	

}
