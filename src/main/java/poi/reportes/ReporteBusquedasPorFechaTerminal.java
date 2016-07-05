package poi.reportes;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioConsultas;

public class ReporteBusquedasPorFechaTerminal extends ReporteAbstracto {
	
	

	@Override
	public JsonArray dumpReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public JsonObject consultasHechasEnTerminalEnciertaFecha(String unaTerminal,LocalDate unaFecha){
		Long unaCantidad;
		unaCantidad = repositorioDeHistorial.cantidaDeConsultasHechasEnUnaTerminalEnUnaFecha(unaTerminal,unaFecha);
		return createResult(unaTerminal,unaFecha,unaCantidad);
		}
	
	

	private JsonObject createResult(String unaTerminal ,LocalDate unaFecha, Long unaCantidad) {
		HashMap<String, String> columns = new HashMap<String, String>();
		columns.put("Terminal", unaTerminal.toString());
		columns.put("Fecha", unaFecha.toString());
		columns.put("Cantidad", unaCantidad.toString());
		return super.createResult(columns);
	}

	
}
