package poi.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Feriados {
	
	private List<LocalDateTime> feriados = new ArrayList<LocalDateTime>();

	public List<LocalDateTime> getFeriados() {
		return feriados;
	}

	public void agregarFeriados(LocalDateTime unFeriado){
		feriados.add(unFeriado);
	}
}
