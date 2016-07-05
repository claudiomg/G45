package poi.reportes;

import java.time.LocalDate;
import java.util.HashMap;

import com.google.gson.JsonObject;

import poi.utilidades.Consulta;

public class ReporteBusquedasPorFecha extends ReporteAbstracto {

	@Override
	public void dumpReport() {
		HashMap<LocalDate, Integer> fechas = this.recolectarFechas();
		this.loadResults(fechas);
	}


	private void loadResults(HashMap<LocalDate, Integer> fechas) {
		for (LocalDate fecha : fechas.keySet()){
			this.createResult(fecha, fechas.get(fecha));
		}
	}

	private HashMap<LocalDate, Integer> recolectarFechas() {
		//las llaves son fechas los valores son numeros
		HashMap<LocalDate, Integer> fechas = new HashMap<LocalDate, Integer>();
		for ( Consulta consulta : this.getRepository().getRegistros() ){
			LocalDate fecha = consulta.getFecha();
			Integer oldValue = fechas.getOrDefault(fecha, 0);
			Integer newValue = Integer.sum(oldValue, 1);
			fechas.put(fecha, newValue);
		}
		return fechas;
	}
	private JsonObject createResult(LocalDate unaFecha, Integer integer) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Fecha", unaFecha.toString());
		columns.put("Cantidad", integer.toString());
		return super.createResult(columns);
	}
}
