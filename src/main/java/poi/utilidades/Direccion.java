package poi.utilidades;

import java.util.ArrayList;
import java.util.List;

public class Direccion {
	private String calle = new String();
	private String numero = new String();
	private String piso = new String();
	private String departamento = new String();
	private String unidad = new String();
	private String codigoPostal = new String();
	private String entreCalleDerecha = new String();
	private String entreCalleIzquierda = new String();
	private String barrio = new String();
	private String localidad = new String();
	private String pais = new String();
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

}
