package poi.reportes;

import java.time.LocalDate;
import java.util.HashMap;

import com.google.gson.JsonObject;

import poi.modelo.usuario.Terminal;
import poi.utilidades.Consulta;

public class ReporteBusquedasPorFechaTerminal extends ReporteAbstracto {
	
	@Override
	public void dumpReport() {
		HashMap<Terminal, HashMap<LocalDate, Integer>> terminales = this.recolectarTerminales();
		this.loadResults(terminales);
	}
	
	private void loadResults(HashMap<Terminal, HashMap<LocalDate, Integer>> terminales) {
		for (Terminal terminal : terminales.keySet()){
			for (LocalDate fecha : terminales.get(terminal).keySet()){
				this.createResult(
					terminal.getUsuario(),
					fecha,
					terminales.get(terminal).get(fecha)
				);	
			}
		}
	}
	public HashMap<Terminal, HashMap<LocalDate, Integer>> recolectarTerminales() {
		//las llaves son fechas los valores son numeros
		HashMap<Terminal, HashMap<LocalDate, Integer>> terminales = new HashMap<>();
		for ( Consulta consulta : this.getRepository().getRegistros() ){
			Terminal terminal = consulta.getUser();
			HashMap<LocalDate, Integer> fechas = terminales.getOrDefault(terminal,new HashMap<LocalDate,Integer>());
			Integer oldValue = fechas.getOrDefault(consulta.getFecha(), 0);
			Integer newValue = Integer.sum(oldValue, 1);
			fechas.put(consulta.getFecha(), newValue);
			terminales.put(terminal, fechas);
		}
		return terminales;
	}

	private JsonObject createResult(String unaTerminal ,LocalDate unaFecha, Integer integer) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Terminal", unaTerminal.toString());
		columns.put("Fecha", unaFecha.toString());
		columns.put("Cantidad", integer.toString());
		return super.createResult(columns);
	}

	
}
