package poi.ABMPoisTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.modelo.usuario.Administrador;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;
import poi.utilidades.Feriados;

public class ABMPoisTest {

	RepositorioAbstracto repositorio = RepositorioPOI.getInstance();
	Administrador admin = new Administrador(repositorio);
	
	ArrayList<String> etiquetasColectivo = new ArrayList<String>();
	ArrayList<String> etiquetasColectivo2 = new ArrayList<String>();
	ArrayList<String> etiquetas = new ArrayList<String>();
	ArrayList<String> etiquetasKiosco= new ArrayList<String>();
	
	ServicioDeCGP unServicio = new ServicioDeCGP();
	ServicioDeCGP otroServicio = new ServicioDeCGP();
	
	TimeRange rangoInferiorDeLaSemana = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
	TimeRange rangoSuperiorDeLaSemana = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(20,30,0));
	TimeRange rangoDelSabado = new TimeRange (LocalTime.of(8, 30, 0),LocalTime.of(16, 0, 0));
	TimeRange rangoInferior = new TimeRange(LocalTime.of(11,0,0),LocalTime.of(15,0,0));
	TimeRange rangoSuperior = new TimeRange(LocalTime.of(17,0,0),LocalTime.of(22,0,0));
	DisponibilidadHoraria lunes = new DisponibilidadHoraria(DayOfWeek.MONDAY);
	DisponibilidadHoraria martes = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
	DisponibilidadHoraria miercoles = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
	DisponibilidadHoraria jueves = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
	DisponibilidadHoraria viernes = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
	DisponibilidadHoraria sabado = new DisponibilidadHoraria (DayOfWeek.SATURDAY);
	DisponibilidadHoraria lunes1 = new DisponibilidadHoraria(DayOfWeek.MONDAY);
	DisponibilidadHoraria martes1 = new DisponibilidadHoraria(DayOfWeek.TUESDAY);
	DisponibilidadHoraria miercoles1 = new DisponibilidadHoraria(DayOfWeek.WEDNESDAY);
	DisponibilidadHoraria jueves1 = new DisponibilidadHoraria (DayOfWeek.THURSDAY);	
	DisponibilidadHoraria viernes1 = new DisponibilidadHoraria (DayOfWeek.FRIDAY);
	DisponibilidadHoraria sabado1 = new DisponibilidadHoraria (DayOfWeek.SATURDAY);
	public ArrayList<DisponibilidadHoraria> disponibilidades = new ArrayList<DisponibilidadHoraria>();
	
	Posicion posicionUno = new Posicion(40.417, 3.703);
	Posicion posicionDos = new Posicion(40.453, 3.68);		
	Posicion posicionTres = new Posicion(40.400, 3.702);
	Posicion posicionCuatro = new Posicion(40.417, 3.68);
	Posicion posicionSiete = new Posicion(40.417, 3.69);	

	List<Posicion> vertices = new ArrayList<Posicion>();

	POI paradaColectivo = new ParadaColectivo(etiquetasColectivo,null);
	POI paradaColectivo2 = new ParadaColectivo(etiquetasColectivo2,null);
	POI CGP1 = new CGP(etiquetas, posicionUno, vertices);
	CGP CGP2 = new CGP(etiquetas, posicionUno, vertices);
	LocalComercial kiosco = new LocalComercial(RadioCercania.Kiosco);
	SucursalBanco banco = new SucursalBanco(etiquetas, posicionDos);
	
	Feriados feriados = new Feriados();
	
	@Before
	public void inicializar(){
		lunes.agregarNuevoRango(rangoInferiorDeLaSemana);
		lunes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		martes.agregarNuevoRango(rangoInferiorDeLaSemana);
		martes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoInferiorDeLaSemana);
		miercoles.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoSuperiorDeLaSemana);
		jueves.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoInferiorDeLaSemana);
		viernes.agregarNuevoRango(rangoSuperiorDeLaSemana);
		sabado.agregarNuevoRango(rangoDelSabado);
		kiosco.addDisponibilidadDeAtencion(lunes);
		kiosco.addDisponibilidadDeAtencion(martes);
		kiosco.addDisponibilidadDeAtencion(miercoles);
		kiosco.addDisponibilidadDeAtencion(jueves);
		kiosco.addDisponibilidadDeAtencion(viernes);
		kiosco.addDisponibilidadDeAtencion(sabado);
		banco.setNombre("Macro");
		banco.setSucursal("Anchorena");
		banco.setGerente("Pablo Perez");
		vertices.add(posicionDos);
		vertices.add(posicionCuatro);
		vertices.add(posicionTres);
		paradaColectivo2.agregarEtiqueta("retiro");
		paradaColectivo.agregarEtiqueta("7");
		paradaColectivo.agregarEtiqueta("retiro");
		paradaColectivo.agregarEtiqueta("samore");		
		CGP1.agregarEtiqueta("comuna 7");
		CGP1.agregarEtiqueta("flores");
		CGP1.agregarEtiqueta("parque chacabuco");		
		kiosco.etiquetas = etiquetasKiosco;
		kiosco.agregarEtiqueta("golosinas");
		kiosco.agregarEtiqueta("cigarrillos");
		kiosco.agregarEtiqueta("fotocopias");
		CGP2.agregarServicio(unServicio);
		admin.agregarPOI(CGP1);
		kiosco.setFeriados(feriados);
		banco.setFeriados(feriados);
		
	}
	
	@After
	public void vaciarRepositorio(){
		admin.removerPOI(CGP1);
		admin.removerPOI(kiosco);
	}
	
	@Test
	public void administradorAgregaPOI(){
		admin.agregarPOI(kiosco);
		Assert.assertTrue(repositorio.getPois().size() == 2);
	}
	
	@Test
	public void administradorBorraPOI(){
		admin.removerPOI(CGP1);
		Assert.assertTrue(repositorio.getPois().size() == 0);
	}

	@Test
	public void administradorAgregaEtiqueta(){
		admin.agregarEtiquetaAPOI(CGP1, "Villa Crespo");
		Assert.assertTrue(CGP1.etiquetas.size() == 4);
	}	

	@Test
	public void administradorEliminarEtiqueta(){
		admin.quitarEtiquetaAPOI(CGP1, "flores");
		Assert.assertTrue(CGP1.etiquetas.size() == 2);
	}	

	@Test
	public void administradorModificarLatitud(){
		admin.cambiarLatitudPOI(CGP1, 30.417);
		Assert.assertTrue(CGP1.posicion.getLatitud() == 30.417);
	}	

	@Test
	public void administradorModificarLongitud(){
		admin.cambiarLongitudPOI(CGP1, 30.417);
		Assert.assertTrue(CGP1.posicion.getLongitud() == 30.417);
	}	

	@Test
	public void administradorAgregaServicioACGP(){
		admin.agregarServicioACGP(CGP2, otroServicio);
		Assert.assertTrue(CGP2.getServicios().size() == 2);
	}	

	@Test
	public void administradorQuitarServicioACGP(){
		admin.quitarServicioACGP(CGP2, unServicio);
		Assert.assertTrue(CGP2.getServicios().size() == 0);
	}	

	@Test
	public void administradorAgregarVerticeACGP(){
		admin.agregarVerticeCGP(CGP2, posicionSiete);
		Assert.assertTrue(CGP2.getVerticesComuna().size() == 4);
	}	

	@Test
	public void administradorQuitarVerticeACGP(){
		admin.quitarVerticeCGP(CGP2, posicionDos);
		Assert.assertTrue(CGP2.getVerticesComuna().size() == 2);
	}	

	@Test
	public void administradorModificaNombreBanco(){
		admin.modificarNombreBanco(banco, "Rio");
		Assert.assertTrue(banco.getNombre() == "Rio");
	}	

	@Test
	public void administradorModificaSucursalBanco(){
		admin.modificarNombreSucursalBanco(banco, "Larrea");
		Assert.assertTrue(banco.getSucursal() == "Larrea");
	}	

	@Test
	public void administradorModificaNombreGerenteBanco(){
		admin.modificarNombreGerenteBanco(banco, "Jose Marmol");
		Assert.assertTrue(banco.getGerente() == "Jose Marmol");
	}	

	@Test
	public void administradorModificarDisponibilidadHorariaPOI(){
		lunes1.agregarNuevoRango(rangoInferior);
		lunes1.agregarNuevoRango(rangoSuperior);
		martes1.agregarNuevoRango(rangoInferior);
		martes1.agregarNuevoRango(rangoSuperior);
		miercoles1.agregarNuevoRango(rangoInferior);
		miercoles1.agregarNuevoRango(rangoSuperior);
		jueves1.agregarNuevoRango(rangoSuperior);
		jueves1.agregarNuevoRango(rangoInferior);
		viernes1.agregarNuevoRango(rangoInferior);
		viernes1.agregarNuevoRango(rangoSuperior);
		disponibilidades.add(lunes1);
		disponibilidades.add(martes1);
		disponibilidades.add(miercoles1);
		disponibilidades.add(jueves1);
		disponibilidades.add(viernes1);		
		admin.modificarDisponibilidadHorariaAPOI(kiosco, disponibilidades);
		Assert.assertTrue(kiosco.estaDisponible(LocalDateTime.of(2016, 6, 01, 14, 10,50)));
	}		
	
}
