package poi.reportes;

import java.util.HashMap;

import com.google.gson.JsonObject;

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
			this.createResult(terminal.getUsuario(), terminales.get(terminal));
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

	private JsonObject createResult(String unaTerminal, Integer integer) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Terminal", unaTerminal.toString());
		columns.put("Cantidad", integer.toString());
		return super.createResult(columns);
	}

}
