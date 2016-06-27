package poi.utilidades;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class ExcepcionHorarioCambiado {
	private List<TimeRange> horariosCambiados = new ArrayList<TimeRange>();
	private LocalDate diaYMes;
	
	public ExcepcionHorarioCambiado(LocalDate unaFecha){
		this.diaYMes = unaFecha;
	}
	
	public void agregarRangoCambiado(TimeRange rango){
		horariosCambiados.add(rango);
			
	}

}