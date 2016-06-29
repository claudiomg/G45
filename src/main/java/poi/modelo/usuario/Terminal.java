package poi.modelo.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;
import poi.utilidades.BusquedaDeBancos;
import poi.utilidades.HistorialConsulta;

@Entity
@Table(name = "usuarios")
public class Terminal  implements WithGlobalEntityManager {
	
	@Id
	@GeneratedValue
	@Column(name = "USUARIO_ID")
	private Long usuarioId;
	
	@Column(name = "Login_Usuario")
	private String login;

	@Column(name = "Password")
	private String password;

	@Transient
	private Posicion posicion;
	@Transient	
	private static List<Consulta> consultas = new ArrayList<Consulta>();
	@Transient
	private Consulta consultaActiva;

	private Rol rol = Rol.USUARIO;
	
	private String nombre;
	
	public Terminal() {
	}
    
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public boolean estoyCercaDe(POI poi) {
		this.consultaActiva.generarHistorial(poi.toString());
		return this.consultaActiva.sonCercanos(this.posicion, poi);
	}

	public boolean estaDisponible(POI poi){
		this.consultaActiva.generarHistorial(poi.toString());
		return this.consultaActiva.estaDisponible(poi);
	}
	
	public void buscarPOIPorPalabra(String palabra){
		this.consultaActiva.generarHistorial(palabra);
		this.consultaActiva.buscarPorPalabra(palabra);
		this.consultaActiva.buscarPorPalabraEnExterno(palabra);
		
	}
	
	public Consulta getConsultaActiva() {
		return consultaActiva;
	}

	public void agregarConsulta(Consulta unaConsulta, LocalDate fecha){
		consultas.add(unaConsulta);
		this.consultaActiva = unaConsulta;
		this.consultaActiva.setHistorial(fecha, this.nombre);
		
	}
	

	public void persistir(Terminal usuario){
		entityManager().getTransaction().begin();
		entityManager().persist(usuario);
		entityManager().getTransaction().commit();
	}
	

	public int numeroDePoisEncontrados() {
       int cantidadEncontrados= (this.getConsultaActiva().getPoisEncontrados().size() + this.getConsultaActiva().getPoisEncontradosEnExterno().size());
       return cantidadEncontrados;
	}
	
	public List<SucursalBanco> buscarBancos(String nombreBanco,String servicio){
		this.consultaActiva.generarHistorial(nombreBanco+' '+servicio);
		return consultaActiva.buscarBancosPorNombreYServicio(nombreBanco,servicio);
		
	}

	}
