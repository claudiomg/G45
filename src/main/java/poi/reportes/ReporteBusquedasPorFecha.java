package poi.reportes;

import java.time.LocalDate;
import java.util.HashMap;

import poi.utilidades.Consulta;

public class ReporteBusquedasPorFecha extends ReporteAbstracto {

	@Override
	public void dumpReport() {
		HashMap<LocalDate, Integer> fechas = this.recolectarFechas();
		this.loadResults(fechas);
	}


	private void loadResults(HashMap<LocalDate, Integer> fechas) {
		//este metodo se encarga de formatear los resultados para que los pueda procesar el controller
		for (LocalDate fecha : fechas.keySet()){
			HashMap<String, Object> result = new HashMap<>();
			result.put("Fecha", fecha.toString());
			result.put("Cantidad", fechas.get(fecha));
			this.addResult(result);
		}
	}

	public HashMap<LocalDate, Integer> recolectarFechas() {
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
}
