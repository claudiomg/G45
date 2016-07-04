package poi.repositorios;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositorioAbstracto {
	
	public List<Object> registros = new ArrayList <Object>(); 

	public void agregarRegistro(Object unRegistro){
		this.registros.add(unRegistro);
	}
	public void eliminarRegistro(Object unRegistro){
		this.registros.remove(unRegistro);
	}
	public void cleanRepository() {
		this.registros = new ArrayList <Object>();
	}
	public List<Object> getRegistros() {
		return this.registros;
	}
}
