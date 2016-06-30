package poi.reportes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReporteBusquedasPorFecha extends ReporteAbstracto {

	@Override
	public JsonArray dumpReport() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public JsonObject consultasHechasEnciertaFecha(LocalDate unaFecha){
		Long unaCantidad;

		unaCantidad = (long) repositorioDeHistorial.filtrarConsultaPorFecha(repositorioDeHistorial.getHistoriales(),unaFecha).size();
		return createResult(unaFecha,unaCantidad);
		}

	private JsonObject createResult(LocalDate unaFecha, Long unaCantidad) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Fecha", unaFecha.toString());
		columns.put("Cantidad", unaCantidad.toString());
		return super.createResult(columns);
	}
}
