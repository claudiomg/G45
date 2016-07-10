package poi.controllers;

import java.util.HashMap;
import java.util.List;

import poi.modelo.usuario.UsuarioPOI;
import poi.reportes.ReportByTerminalAndDateTransformer;
import poi.reportes.ReporteBusquedasPorFecha;
import poi.reportes.ReporteBusquedasPorFechaTerminal;
import poi.reportes.ReporteBusquedasPorTerminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class QueryReportsController {
	public ModelAndView render(Request request, Response response){
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return new ModelAndView(new HashMap<>(), "index.html");
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("selectReportValue", "");
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView reportByDate(Request request, Response response){
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return new ModelAndView(new HashMap<>(), "index.html");
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		ReporteBusquedasPorFecha reporte = new ReporteBusquedasPorFecha();
		reporte.dumpReport();
		viewModel.put("hasResults", !(reporte.getResults().isEmpty()));
		viewModel.put("results", reporte.getResults());
		viewModel.put("selectReportValue", "byDate");
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView reportByTerminal(Request request, Response response){
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return new ModelAndView(new HashMap<>(), "index.html");
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		ReporteBusquedasPorTerminal reporte = new ReporteBusquedasPorTerminal();
		reporte.dumpReport();
		viewModel.put("hasResults", !(reporte.getResults().isEmpty()));
		viewModel.put("results", reporte.getResults());
		viewModel.put("selectReportValue", "byTerminal");
		return new ModelAndView(viewModel, "queryReports.html");
	}
	
	public ModelAndView reportByTerminalAndDate(Request request, Response response){
		UsuarioPOI user = request.session().attribute("user");
		if (user == null){
			response.redirect("/");
			return new ModelAndView(new HashMap<>(), "index.html");
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		ReporteBusquedasPorFechaTerminal reporte = new ReporteBusquedasPorFechaTerminal();
		reporte.dumpReport();
		ReportByTerminalAndDateTransformer reportTransformer = new ReportByTerminalAndDateTransformer(reporte.getResults());
		viewModel.put("hasResults", !reportTransformer.getResults().isEmpty());
		viewModel.put("results", reportTransformer.getResults());
		viewModel.put("terminals", reportTransformer.getTerminals());
		viewModel.put("selectReportValue", "byTerminalAndDate");
		return new ModelAndView(viewModel, "queryReports.html");
	}
}
