package poi.utilidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@Entity
@Table(name="Direcciones")
public class Direccion implements WithGlobalEntityManager {
	@Id
	@GeneratedValue
	@Column(name = "direccionId")
	private Long direccionId;
	@Column(name = "calle")
	private String calle = new String();
	@Column(name = "numero")
	private String numero = new String();
	@Column(name = "piso")
	private String piso = new String();
	@Column(name = "departamento")
	private String departamento = new String();
	@Column(name = "unidad")
	private String unidad = new String();
	@Column(name = "codigoPostal")
	private String codigoPostal = new String();
	@Column(name = "entreCalleDerecha")
	private String entreCalleDerecha = new String();
	@Column(name = "entreCalleIzquierda")
	private String entreCalleIzquierda = new String();
	@Column(name = "barrio")
	private String barrio = new String();
	@Column(name = "localidad")
	private String localidad = new String();
	@Column(name = "pais")
	private String pais = new String();
	@Column(name = "provincia")
	private String provincia = new String();
	
	public List<String> getEtiquetas(){
		List<String> lista = new ArrayList<String>();
		lista.add(this.getCalle());
		lista.add(this.getNumero());
		lista.add(this.getPiso());
		lista.add(this.getDepartamento());
		lista.add(this.getLocalidad());
		lista.add(this.getCodigoPostal());
		lista.add(this.getEntreCalleDerecha());
		lista.add(this.getEntreCalleDerecha());
		lista.add(this.getBarrio());
		lista.add(this.getLocalidad());
		lista.add(this.getPais());
		lista.add(this.getProvincia());
		return lista;
	}
	
	public Direccion(){
		
	}
	
	public Long getDireccionId() {
		return direccionId;
	}

	public void setDireccionId(Long direccionId) {
		this.direccionId = direccionId;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEntreCalleDerecha() {
		return entreCalleDerecha;
	}

	public void setEntreCalleDerecha(String entreCalleDerecha) {
		this.entreCalleDerecha = entreCalleDerecha;
	}

	public String getEntreCalleIzquierda() {
		return entreCalleIzquierda;
	}

	public void setEntreCalleIzquierda(String entreCalleIzquierda) {
		this.entreCalleIzquierda = entreCalleIzquierda;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void persistir(Direccion direccion){
		entityManager().getTransaction().begin();
		entityManager().persist(direccion);
		entityManager().getTransaction().commit();
	}	

}
