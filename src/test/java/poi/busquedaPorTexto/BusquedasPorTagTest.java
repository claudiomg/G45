package poi.busquedaPorTexto;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.finders.FilterByTag;
import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioCGPExternos;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class BusquedasPorTagTest {
    
	Direccion direccion = new Direccion();
	Posicion posicion = new Posicion(0.0, 0.0);
	Terminal usuario = new Terminal("Abasto");
	RepositorioAbstractoPOI repositorioLocal = RepositorioPOI.getInstance();
	RepositorioAbstractoPOI repositorioExternoBancos = RepositorioBancosExternos.getInstance();
	RepositorioAbstractoPOI repositorioExternoCGP = RepositorioCGPExternos.getInstance();
	
	@Before
	public void initialize(){
		repositorioExternoBancos.cleanRepository();
		repositorioExternoCGP.cleanRepository();
		repositorioLocal.cleanRepository();
	}
	
	@Test
	public void obtengoUnBancoDelRepositorioLocal(){
		repositorioLocal.agregarRegistro(new SucursalBanco("Belgrano", posicion, direccion));
		PoiFinder finder = new PoiFinder();
		String palabra = "banco";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 1);
	}
	
	@Test
	public void obtengoUnBancosDelRepositorioLocalApartirDeServicioCajero(){
		SucursalBanco banco1 = new SucursalBanco("Belgrano1", posicion, direccion);
		SucursalBanco banco2 = new SucursalBanco("Belgrano2", posicion, direccion);
		SucursalBanco banco3 = new SucursalBanco("Belgrano3", posicion, direccion);
		banco1.agregarServicio("Prestamo");
		banco2.agregarServicio("Prestamo");
		banco3.agregarServicio("Cajero");
		repositorioLocal.agregarRegistro(banco1);
		repositorioLocal.agregarRegistro(banco2);
		repositorioLocal.agregarRegistro(banco3);
		PoiFinder finder = new PoiFinder();
		String palabra = "cajero";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 1);
	}
	
	@Test
	public void obtengoUnBancosDelRepositorioLocalApartirDeServicioPrestamo(){
		SucursalBanco banco1 = new SucursalBanco("Belgrano1", posicion, direccion);
		SucursalBanco banco2 = new SucursalBanco("Belgrano2", posicion, direccion);
		SucursalBanco banco3 = new SucursalBanco("Belgrano3", posicion, direccion);
		banco1.agregarServicio("Prestamo");
		banco2.agregarServicio("Prestamo");
		banco3.agregarServicio("Cajero");
		repositorioLocal.agregarRegistro(banco1);
		repositorioLocal.agregarRegistro(banco2);
		repositorioLocal.agregarRegistro(banco3);
		PoiFinder finder = new PoiFinder();
		String palabra = "prestamo";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 2);
	}
	
	@Test
	public void obtengoDosBancosDelRepositorioLocalyExterno(){
		repositorioExternoBancos.agregarRegistro(new SucursalBanco("Caballito", posicion, direccion));
		repositorioLocal.agregarRegistro(new SucursalBanco("Belgrano", posicion, direccion));
		PoiFinder finder = new PoiFinder();
		String palabra = "banco";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addRepository(repositorioExternoBancos);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 2);
	}
	
	@Test
	public void obtengoDosCGPDelRepositorioLocalyExterno(){
		repositorioExternoCGP.agregarRegistro(new CGP("Centro Belgrano", posicion, direccion, new ArrayList<Posicion>()));
		repositorioLocal.agregarRegistro(new CGP("Centro Saavedra", posicion, direccion, new ArrayList<Posicion>()));
		repositorioLocal.agregarRegistro(new SucursalBanco("Belgrano", posicion, direccion));
		PoiFinder finder = new PoiFinder();
		String palabra = "centro";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addRepository(repositorioExternoCGP);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 2);
	}
	
	@Test
	public void buscoPorDireccionEnPoisDeDistintoTipo(){
		direccion.setBarrio("Chacarita");
		repositorioLocal.agregarRegistro(new SucursalBanco("CH7", posicion, direccion));
		repositorioLocal.agregarRegistro(new CGP("Centro Chaca", posicion, direccion, new ArrayList<Posicion>()));
		repositorioLocal.agregarRegistro(new ParadaColectivo("Estacion Chacarita", posicion, direccion));
		repositorioLocal.agregarRegistro(new LocalComercial("Kiosco Chaca", posicion, direccion, RadioCercania.Kiosco));
		repositorioLocal.agregarRegistro(new LocalComercial("Libreria Chaca", posicion, direccion, RadioCercania.LibreriaEscolar));
		PoiFinder finder = new PoiFinder();
		String palabra = "Chacarita";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addRepository(repositorioExternoBancos);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 5);
	}
	
	@Test
	public void buscoPorDireccionErroneaEnPoisDeDistintoTipo(){
		direccion.setBarrio("Chacarita");
		repositorioLocal.agregarRegistro(new SucursalBanco("CH7", posicion, direccion));
		repositorioLocal.agregarRegistro(new CGP("Centro Chaca", posicion, direccion, new ArrayList<Posicion>()));
		repositorioLocal.agregarRegistro(new ParadaColectivo("Estacion Chacarita", posicion, direccion));
		repositorioLocal.agregarRegistro(new LocalComercial("Kiosco Chaca", posicion, direccion, RadioCercania.Kiosco));
		repositorioLocal.agregarRegistro(new LocalComercial("Libreria Chaca", posicion, direccion, RadioCercania.LibreriaEscolar));
		PoiFinder finder = new PoiFinder();
		String palabra = "Lugano";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addRepository(repositorioExternoBancos);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 0);
	}
	
	@Test
	public void buscoPorPoisDeDistintoTipo(){
		direccion.setBarrio("Chacarita");
		repositorioLocal.agregarRegistro(new SucursalBanco("CH7", posicion, direccion));
		repositorioLocal.agregarRegistro(new CGP("Centro Chaca", posicion, direccion, new ArrayList<Posicion>()));
		repositorioLocal.agregarRegistro(new ParadaColectivo("Estacion Chacarita", posicion, direccion));
		repositorioLocal.agregarRegistro(new LocalComercial("Kiosco Chaca", posicion, direccion, RadioCercania.Kiosco));
		repositorioLocal.agregarRegistro(new LocalComercial("Libreria Chaca", posicion, direccion, RadioCercania.LibreriaEscolar));
		PoiFinder finder = new PoiFinder();
		String palabra = "local";
		Consulta consulta = new Consulta(usuario,palabra);
		finder.setConsulta(consulta);
		finder.addRepository(repositorioLocal);
		finder.addRepository(repositorioExternoBancos);
		finder.addFilter(new FilterByTag(palabra));
		finder.search();
		Assert.assertEquals(finder.getResults().size(), 2);
	}
}
