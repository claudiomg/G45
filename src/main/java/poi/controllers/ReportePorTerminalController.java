package poi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import poi.modelo.usuario.Terminal;
import poi.reportes.ReporteBusquedasPorTerminal;
import poi.repositorios.RepositorioConsultas;
import poi.utilidades.Consulta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ReportePorTerminalController {
	public ModelAndView mostrar(Request request, Response response){
		ReporteBusquedasPorTerminal reporte = new ReporteBusquedasPorTerminal();
		HashMap<Terminal, Integer> resultado = reporte.recolectarTerminales();
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("hasResults", !resultado.isEmpty());
		viewModel.put("resultado",armarLista(resultado));
		return new ModelAndView(viewModel, "BusquedaPorTerminal.html");
	}

	private List<HashMap<String, Object>> armarLista(HashMap<Terminal, Integer> resultado) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();		
		Iterator it = resultado.entrySet().iterator();
		while (it.hasNext()) {
			HashMap<String,Object> element = new HashMap<String,Object>();		    
			Map.Entry e = (Map.Entry)it.next();
			Terminal terminal = (Terminal) e.getKey();
			element.put("nombre", terminal.getUsuario());
			element.put("cantidad", e.getValue());
			array.add(element);
		}
		return array;
	}
}
