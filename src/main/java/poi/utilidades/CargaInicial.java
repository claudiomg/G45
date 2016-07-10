package poi.utilidades;

import java.time.LocalDateTime;
import java.util.HashMap;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.ParadaColectivo;
import poi.modelo.puntoDeInteres.RadioCercania;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioConsultas;
import poi.repositorios.RepositorioPOI;
import poi.repositorios.RepositorioUsuarios;

public class CargaInicial {
	public static CargaInicial instance = null;
	RepositorioPOI repositorioPOI = RepositorioPOI.getInstance();
	private HashMap<String, Direccion> direcciones = new HashMap<String, Direccion>();
	private HashMap<String, Posicion> posiciones = new HashMap<String, Posicion>();
	RepositorioConsultas repositorioConsultas = RepositorioConsultas.getInstance();
	UsuarioPOI admin1;
	UsuarioPOI admin2;
	Terminal terminal1;
	Terminal terminal2;
	Terminal terminal3;
	
	private CargaInicial(){		
	};
	public static CargaInicial getInstance() {
		if(instance == null) {
			instance = new CargaInicial();
		}
		return instance;
	}
	
	public void inicializar(){
		
		this.inicializarUsuarios();
		this.inicializarDirecciones();
		this.inicializarPosiciones();
		this.inicializarPOIs();
		this.inicializarConsultas();
		//this.inicializarBancosExternos();
		//this.inicializarCgpExternos();
		//this.inicializarConsultas();
	}

	
	private void inicializarPOIs() {
		
		//parada colectivos
			//Linea 36
				this.crearParada("Linea 36","PlazaItalia");
				this.crearParada("Linea 36","Rivadavia4975");
				this.crearParada("Linea 36","Rivadavia5243");
				this.crearParada("Linea 36","Rivadavia5010");
			//Linea 36
				this.crearParada("Linea 141","PlazaItalia");
				this.crearParada("Linea 141","Rivadavia4975");
				this.crearParada("Linea 141","Rivadavia5243");
				this.crearParada("Linea 141","Rivadavia5010");
			//Linea 36
				this.crearParada("Linea 88","PlazaItalia");
				this.crearParada("Linea 88","Rivadavia4975");
				this.crearParada("Linea 88","Rivadavia5243");
				this.crearParada("Linea 88","Rivadavia5010");
		//locales comerciales
				this.crearKiosco("Kiosco rivadavia","Rivadavia5243");
				this.crearKiosco("Kiosco PEPE","Corrientes3040");
				this.crearKiosco("Kiosco lULU","SantaFe3025");
				this.crearKiosco("Kiosco estacion","PlazaItalia");
				this.crearLibreria("Libreria escolar","Corrientes3040");
				this.crearLibreria("Libreria rivadavia","Rivadavia5010");
				this.crearLibreria("Libreria santa","SantaFe3025");
				this.crearLibreria("Libreria Italia","PlazaItalia");
	}
	
	private void crearKiosco(String nombre, String key) {
		LocalComercial local;
		local = new LocalComercial(nombre, this.getPosicion(key), this.getDireccion(key),RadioCercania.Kiosco);
		repositorioPOI.agregarRegistro(local);
	}
	private void crearLibreria(String nombre, String key) {
		LocalComercial local;
		local = new LocalComercial(nombre, this.getPosicion(key), this.getDireccion(key),RadioCercania.LibreriaEscolar);
		repositorioPOI.agregarRegistro(local);
	}
	private void crearParada(String linea, String key) {
		ParadaColectivo parada;
		parada = new ParadaColectivo(linea, this.getPosicion(key), this.getDireccion(key));
		repositorioPOI.agregarRegistro(parada);
	}
	private void inicializarUsuarios() {
		RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
		
		//admin1
		admin1 = new Administrador("admin1");
		admin1.setPassword("admin1");
		((Administrador) admin1).setMail("mujica.juancarlos@gmail.com");
		repositorio.agregarRegistro(admin1);
		
		//admin2
		admin2 = new Administrador("admin2");
		admin2.setPassword("admin2");
		((Administrador) admin2).setMail("lalala@gmail.com");
		repositorio.agregarRegistro(admin2);
			
		//terminal1
		terminal1 = new Terminal("Abasto");
		terminal1.setPassword("abasto");
		((Terminal) terminal1).setPosicion(new Posicion(-34.603329, -58.410789));
		repositorio.agregarRegistro(terminal1);
		
		//terminal2
		terminal2 = new Terminal("Caballito");
		terminal2.setPassword("caballito");
		((Terminal) terminal2).setPosicion(new Posicion(-34.619155, -58.437811));
		repositorio.agregarRegistro(terminal2);
		
		//terminal3
		terminal3 = new Terminal("Palermo");
		terminal3.setPassword("palermo");
		((Terminal) terminal3).setPosicion(new Posicion(-34.588315, -58.410690));
		repositorio.agregarRegistro(terminal3);
	}
	
	private void inicializarPosiciones() {
		this.posiciones.put("PlazaItalia", new Posicion(-34.58108,-58.42105));
		this.posiciones.put("SantaFe3259", new Posicion(-34.588304,-58.410962));
		this.posiciones.put("SantaFe3354", new Posicion(-34.587644,-58.41377));
		this.posiciones.put("SantaFe3025", new Posicion(-34.590771,-58.407982));
		this.posiciones.put("Rivadavia5010", new Posicion(-34.618473,-58.436714));
		this.posiciones.put("Rivadavia5157", new Posicion(-34.619155,-58.438446));
		this.posiciones.put("Rivadavia5243", new Posicion(-34.619637,-58.439476));
		this.posiciones.put("Rivadavia4975", new Posicion(-34.617908,-58.435673));
		this.posiciones.put("Corrientes3240", new Posicion(-34.604054,-58.410911));
		this.posiciones.put("Corrientes3140", new Posicion(-34.604091,-58.409631));
		this.posiciones.put("Corrientes3040", new Posicion(-34.604249,-58.408142));
		this.posiciones.put("Corrientes3340", new Posicion(-34.604122,-58.41223));
	}
	private void inicializarDirecciones() {
		this.createDireccion("PlazaItalia","Palermo","Circular Plaza Italia","4090","C1425BHP");
		this.createDireccion("SantaFe3259","Palermo","Av. Santa Fe","3259","C1425BHP");
		this.createDireccion("SantaFe3354","Palermo","Av. Santa Fe","3354","C1425BHP");
		this.createDireccion("SantaFe3025","Palermo","Av. Santa Fe","3025","C1425BHP");
		this.createDireccion("Rivadavia5010","Caballito","Av. Rivadavia","5010","C1424CES");
		this.createDireccion("Rivadavia5157","Caballito","Av. Rivadavia","5157","C1424CES");
		this.createDireccion("Rivadavia5243","Caballito","Av. Rivadavia","5243","C1424CES");
		this.createDireccion("Rivadavia4975","Caballito","Av. Rivadavia","4975","C1424CES");
		this.createDireccion("Corrientes3240","Abasto","Av. Corrientes","3240","C1193AAR");
		this.createDireccion("Corrientes3140","Abasto","Av. Corrientes","3240","C1193AAR");
		this.createDireccion("Corrientes3040","Abasto","Av. Corrientes","3240","C1193AAR");
		this.createDireccion("Corrientes3340","Abasto","Av. Corrientes","3240","C1193AAR");
		
	}
	private void createDireccion(String key, String barrio, String calle, String altura, String cp) {
		Direccion direccion = new Direccion();
		direccion.setBarrio(barrio);
		direccion.setCalle(calle);
		direccion.setNumero(altura);
		direccion.setCodigoPostal(cp);
		direccion.setLocalidad("Capital Federal");
		direccion.setPais("Argentina");
		this.setDireccion(key,direccion);		
	}
	
	private void inicializarConsultas(){
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 8, 01, 8, 00));
		this.createConsulta(terminal1,"palabra buscada",LocalDateTime.of(2016, 8, 01, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 23, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 23, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 24, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 30, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 4, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 4, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 9, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 9, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 9, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 8, 1, 8, 00));
		this.createConsulta(terminal2,"palabra buscada",LocalDateTime.of(2016, 8, 2, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 26, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 26, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 27, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 28, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 29, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 06, 29, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 1, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 1, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 1, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 05, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 6, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 8, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 9, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 9, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 15, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 26, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 07, 28, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 8, 5, 8, 00));
		this.createConsulta(terminal3,"palabra buscada",LocalDateTime.of(2016, 8, 5, 8, 00));
	}
	
	private void createConsulta(Terminal usuario, String palabra,LocalDateTime fecha) {
		Consulta consulta = new Consulta(usuario, palabra);
		consulta.setComienzoProceso(fecha);
		repositorioConsultas.agregarRegistro(consulta);
	}
	private void setDireccion(String key, Direccion direccion) {
		this.direcciones.put(key, direccion);
	}
	private Direccion getDireccion(String key) {
		return this.direcciones.get(key);
	}
	private Posicion getPosicion(String key) {
		return this.posiciones.get(key);
	}
}
