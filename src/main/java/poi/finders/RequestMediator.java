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
		return this.getConcreteRequest().queryParams("stringFilter");
	}
	
	public boolean enabledDisponibilityFilter() {
		String val = this.getConcreteRequest().queryParams("disponibilityFilter");
		if (val == null){
			return false;
		}
		return val.equals("ON");
	}
	
	public boolean enabledProximityFilter() {
		String val = this.getConcreteRequest().queryParams("proximityFilter");
		if (val == null){
			return false;
		}
		return val.equals("ON");
	}
	
	public boolean enabledSearchOfCgpService() {
		String val = this.getConcreteRequest().queryParams("cgpServiceFilter");
		if (val == null){
			return false;
		}
		return val.equals("ON");
	}

	public boolean enabledSearchOfBank() {
		String val = this.getConcreteRequest().queryParams("bankFilter");
		if (val == null){
			return false;
		}
		return val.equals("ON");
	}
	
	public LocalDateTime getDateAndTimeForDisponibility() {
		//De ser necesario devolver la fecha y hora seleccionada en el cliente, por ahora solo devuelve la del sistema
		return LocalDateTime.now();
	}
	
	public Posicion getPositionForProximity() {
		//Double longitud = new Double(this.getConcreteRequest().queryParams("longitud"));
		//Double latitud = new Double(this.getConcreteRequest().queryParams("latitud"));
		//return new Posicion(latitud, longitud);
		//los metodos comentados sirven para q la proximidad sea dinamica
		return ((Terminal) this.getConcreteRequest().session().attribute("user")).getPosicion();
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
