package poi.finders;

import java.time.LocalDateTime;

import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import poi.utilidades.Posicion;
import spark.Request;

public class RequestMediator {

	private Request concreteRequest;

	public RequestMediator(Request request) {
		this.setConcreteRequest(request);
	}
	
	public String palabraBuscada() {
		return this.getConcreteRequest().queryParams("palabra");
	}
	
	public boolean enabledDisponibilityFilter() {
		return this.getConcreteRequest().queryParams("disponibilityFilter").equals("ON");
	}
	
	public boolean enabledProximityFilter() {
		return this.getConcreteRequest().queryParams("proximityFilter").equals("ON");
	}
	
	public boolean enabledSearchOfCgpService() {
		return this.getConcreteRequest().queryParams("cgpServiceFilter").equals("ON");
	}

	public boolean enabledSearchOfBank() {
		return this.getConcreteRequest().queryParams("bankFilter").equals("ON");
	}
	
	public LocalDateTime getDateAndTimeForDisponibility() {
		//De ser necesario devolver la fecha y hora seleccionada en el cliente, por ahora solo devuelve la del sistema
		return LocalDateTime.now();
	}
	
	public Posicion getPositionForProximity() {
		Double longitud = new Double(this.getConcreteRequest().queryParams("longitud"));
		Double latitud = new Double(this.getConcreteRequest().queryParams("latitud"));
		return new Posicion(latitud, longitud);
	}
	
	public Terminal user() {
		// devuelvo la instancia de usuario alojada en la session
		// cuando se agregue la session modificar este metodo
		//return this.getConcreteRequest().session().attribute("user");
		for ( UsuarioPOI usuario : RepositorioUsuarios.getInstance().getRegistros() ){
			if (!usuario.isAdmin()){
				Terminal terminal = (Terminal) usuario;
				return terminal;
			}
		}
		return this.getConcreteRequest().session().attribute("user");
	}

	private void setConcreteRequest(Request request) {
		this.concreteRequest = request;
	}
	
	private Request getConcreteRequest() {
		return this.concreteRequest;
	}
}
