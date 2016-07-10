package poi.reportes;

import java.time.LocalDate;
import java.util.HashMap;

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
				HashMap<String, Object> result = new HashMap<>();
				result.put("Terminal", terminal.getUsuario());
				result.put("Fecha", fecha.toString());
				result.put("Cantidad", terminales.get(terminal).get(fecha).toString());
				this.addResult(result);;
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
}
