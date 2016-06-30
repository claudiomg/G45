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
	

	public JsonObject consultasHechasEnciertaFecha(Date unaFecha){
		Long unaCantidad;
		String buscar;
		buscar = unaFecha.toString();
		unaCantidad = (long) repositorioDeHistorial.filtraConsultaPorTerminal(repositorioDeHistorial.getHistoriales(),buscar).size();
		return createResult(unaFecha,unaCantidad);
		}

	private JsonObject createResult(Date unaFecha, Long unaCantidad) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Fecha", unaFecha.toString());
		columns.put("Cantidad", unaCantidad.toString());
		return super.createResult(columns);
	}
}
