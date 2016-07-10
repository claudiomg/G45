package poi.reportes;

import java.util.HashMap;

import poi.modelo.usuario.Terminal;
import poi.utilidades.Consulta;

public class ReporteBusquedasPorTerminal extends ReporteAbstracto {

	@Override
	public void dumpReport() {
		HashMap<Terminal, Integer> terminales = this.recolectarTerminales();
		this.loadResults(terminales);
	}
	
	private void loadResults(HashMap<Terminal, Integer> terminales) {
		for (Terminal terminal : terminales.keySet()){
			HashMap<String, Object> result = new HashMap<>();
			result.put("Terminal", terminal.getUsuario());
			result.put("Cantidad", terminales.get(terminal));
			this.addResult(result);
		}
	}

	public HashMap<Terminal, Integer> recolectarTerminales() {
		//las llaves son fechas los valores son numeros
		HashMap<Terminal, Integer> terminales = new HashMap<Terminal, Integer>();
		for ( Consulta consulta : this.getRepository().getRegistros() ){
			Terminal terminal = consulta.getUser();
			Integer oldValue = terminales.getOrDefault(terminal, 0);
			Integer newValue = Integer.sum(oldValue, 1);
			terminales.put(terminal, newValue);
		}
		return terminales;
	}
}
