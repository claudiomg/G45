package poi.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import poi.reportes.ReporteBusquedasPorFecha;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HistorialDeBusquedasController {
	
	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		return new ModelAndView(viewModel, "historialDeBusquedasRealizadas.html");
	}
	
	/*public ModelAndView mostrar(Request request, Response response){						
		ReporteBusquedasPorFecha reporte = new ReporteBusquedasPorFecha();
		HashMap<LocalDate, Integer> resultado = reporte.recolectarFechas();	
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("hasResults", !resultado.isEmpty());
		viewModel.put("results",armarLista(resultado));
		return new ModelAndView(viewModel, "historialDeBusquedasRealizadas.html");
	}

	private List<HashMap<String, Object>> armarLista(HashMap<LocalDate, Integer> resultado) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();		
		Iterator it = resultado.entrySet().iterator();
		while (it.hasNext()) {
			HashMap<String,Object> element = new HashMap<String,Object>();		    
			Map.Entry e = (Map.Entry)it.next();
			element.put("fecha", e.getKey());
			element.put("cantidad", e.getValue());
			array.add(element);
		}
		return array;
	}*/

}
