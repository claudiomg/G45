package poi.finders;

import java.time.LocalDateTime;

import poi.modelo.puntoDeInteres.POI;

public class FilterByDisponibility implements PoiFilter {

	private LocalDateTime unaFechaHora;

	public FilterByDisponibility(LocalDateTime unaFechaHora) {
		this.setUnaFechaHora(unaFechaHora);
	}

	@Override
	public boolean matches(POI unPOI) {
		return unPOI.estaDisponible(unaFechaHora);
	}
	
	public LocalDateTime getUnaFechaHora() {
		return unaFechaHora;
	}

	public void setUnaFechaHora(LocalDateTime unaFechaHora) {
		this.unaFechaHora = unaFechaHora;
	}
}
