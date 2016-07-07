package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorFechaTerminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ReportePorFechaYTerminalController {
	public ModelAndView mostrar(Request request, Response response){
		ReporteBusquedasPorFechaTerminal reporte = new ReporteBusquedasPorFechaTerminal();
		HashMap<Terminal, HashMap<LocalDate, Integer>> resultado = reporte.recolectarTerminales();
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("hasResults", !resultado.isEmpty());
		viewModel.put("resultado",armarLista(resultado));
		return new ModelAndView(viewModel, "BusquedaPorFechaYTerminal.html");
	}

	private List<HashMap<String, Object>> armarLista(HashMap<Terminal, HashMap<LocalDate, Integer>> resultado) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();		
		Iterator it = resultado.entrySet().iterator();
		while (it.hasNext()) {
			HashMap<String,Object> element = new HashMap<String,Object>();		    
			Map.Entry e = (Map.Entry)it.next();
			Terminal terminal = (Terminal) e.getKey();
			element.put("nombre", terminal.getUsuario());
			HashMap<LocalDate, Integer> porFecha = (HashMap<LocalDate, Integer>)e.getValue();
			Iterator it2 = porFecha.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry e2 = (Map.Entry)it2.next();
				element.put("fecha", e2.getKey());				
				element.put("cantidad", e2.getValue() );
				array.add(element);
			}
		}
		return array;
	}
}
