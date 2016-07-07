package poi.finders;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;

public class FilterByPoiType implements PoiFilter {
	
	private List<String> type = new ArrayList<String>();

	@Override
	public boolean matches(POI unPOI) {
		String type = unPOI.getClass().getName();
		return this.getType().stream().anyMatch( key -> key.equals(type));
	}

	public void addType(String type) {
		this.type.add(type);
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}
}
