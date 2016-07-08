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
	
	public String queryParam(String param) {
		if (this.hasQueryParam(param)) return this.getConcreteRequest().queryParams(param);
		return null;
	}
	
	public boolean hasQueryParam(String param) {
		String val = this.getConcreteRequest().queryParams(param);
		return !(val == null);
	}
	
	public boolean queryParam(String param, String value) {
		String val = this.getConcreteRequest().queryParams(param);
		if (this.hasQueryParam(param)) return val.equals(value);
		return false;
	}
	public boolean queryParamOrDefault(String param, String valueParam, boolean defaultValue) {
		//verifico el valor devuelto del cliente si coincide return true; si no esta seteado devuelvo el default
		if (this.hasQueryParam(param)){
			return this.queryParam(param, valueParam);
		}
		return defaultValue;
	}
	public String customValueForQueryParam(String param, String value, String valueIfTrue, String valueIfFalse) {
		if ( this.queryParam(param, value)){
			return valueIfTrue;
		} else {
			return valueIfFalse;
		}
	}
	public String queryParamOrDefault(String param, String defaultValue) {
		//si param viene del cliente devuelvo el valor sino devuelvo el default
		if (this.hasQueryParam(param)){
			return this.queryParam(param);
		}
		return defaultValue;
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
