package poi.reportes;

import java.util.Date;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import poi.modelo.usuario.Terminal;

public class ReporteBusquedasPorFechaTerminal extends ReporteAbstracto {

	@Override
	public JsonArray dumpReport() {
		// TODO Auto-generated method stub
		return null;
	}

	private JsonObject createResult(Terminal unaTerminal ,Date unaFecha, Long unaCantidad) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Terminal", unaTerminal.toString());
		columns.put("Fecha", unaFecha.toString());
		columns.put("Cantidad", unaCantidad.toString());
		return super.createResult(columns);
	}

}
