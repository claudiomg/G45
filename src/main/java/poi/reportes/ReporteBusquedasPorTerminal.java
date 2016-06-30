package poi.reportes;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import poi.modelo.usuario.Terminal;

public class ReporteBusquedasPorTerminal extends ReporteAbstracto {

	@Override
	public JsonArray dumpReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public JsonObject consultasTotalesPorTerminal(String unaTerminal){
		int unaCantidad;
		unaCantidad = repositorioDeHistorial
					  .filtraConsultaPorTerminal(repositorioDeHistorial.getHistoriales(),unaTerminal)
					  .size();
		Long unLong = new Long(unaCantidad);
		return createResult(unaTerminal,unLong);
		
		}

	private JsonObject createResult(String unaTerminal, Long unaCantidad) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Terminal", unaTerminal.toString());
		columns.put("Cantidad", unaCantidad.toString());
		return super.createResult(columns);
	}

}
