package poi.modelo.puntoDeInteres;

import java.util.HashMap;

public class DarDeBaja extends POI{
	int id;
	String deleteAt;
	
	public DarDeBaja() {
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
	@Override
	public void inicializarPalabrasClave() {
		// TODO Auto-generated method stub
		}
	@Override
	public void completeViewData(HashMap<String, Object> element) {
		// no hago nada aca
	}
	


}