package poi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poi.finders.RequestMediator;
import poi.modelo.usuario.Terminal;
import poi.modelo.usuario.UsuarioPOI;
import poi.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalConfigurationController {
	public ModelAndView render(Request request, Response response){
		HashMap<String, Object> viewModel = new HashMap<>();
		RequestMediator requestMediator = new RequestMediator(request);
		List<UsuarioPOI> terminals = RepositorioUsuarios.getInstance().getTerminals();
		
		viewModel.put("hasResults", !terminals.isEmpty());
		viewModel.put("results", this.convertTerminals(terminals,requestMediator));
		return new ModelAndView(viewModel, "terminalConfiguration.html");
	}
	

	private List<HashMap<String, Object>> convertTerminals(List<UsuarioPOI> terminals, RequestMediator request) {
		List<HashMap<String, Object>> array = new ArrayList<HashMap<String,Object>>();
		for ( UsuarioPOI terminal : terminals){
			HashMap<String,Object> element = new HashMap<String,Object>();
			element.put("nombre", terminal.getUsuario());
			element.put("filterByTag", ((Terminal) terminal).canFilterByTag());
			element.put("filterByDisponibility", ((Terminal) terminal).canFilterByDisponibility());
			element.put("filterByProximity", ((Terminal) terminal).canFilterByProximity());
			array.add(element);
		}
		return array;
	}


	public ModelAndView updateConfiguration(Request request, Response response){
		//RequestMediator requestMediator = new RequestMediator(request);
		//HashMap<String, Object> viewModel = this.configViewModel(requestMediator);
		//viewModel.put("hasResults", false);
		//return new ModelAndView(viewModel, "terminalConfiguration.html");
		return null;
	}
}
