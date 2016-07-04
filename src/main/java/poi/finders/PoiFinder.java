package poi.finders;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioAbstracto;
import poi.utilidades.Consulta;

public class PoiFinder {

	private Consulta consulta;
	private List<PoiFilter> filters = new ArrayList<PoiFilter>();
	private List<RepositorioAbstracto> repositories = new ArrayList<RepositorioAbstracto>();
	private List<POI> results = new ArrayList<POI>();

	public void search() {
		this.startSearch();
		for ( RepositorioAbstracto unRepositorio : this.getRepositories()){
			this.searchOn(unRepositorio);
		}
		this.endSearch();
	}

	public void cleanFinder() {
		this.consulta = null;
		this.filters = new ArrayList<PoiFilter>();
		this.repositories = new ArrayList<RepositorioAbstracto>();
		this.results = new ArrayList<POI>();
	}

	private void endSearch() {
		this.getConsulta().setFinProceso(System.currentTimeMillis()/1000);
		this.getConsulta().calcularTiempoProceso();
	}

	private void startSearch() {
		this.getConsulta().setComienzoProceso(System.currentTimeMillis()/1000);
	}

	private void searchOn(RepositorioAbstracto unRepositorio) {
		for ( POI unPoi : unRepositorio.getPois()){
			boolean satisfy = this.satisfyFilters(unPoi);
			if (satisfy){
				this.addResult(unPoi);
			}
		}
	}

	private boolean satisfyFilters(POI unPoi) {
		return this.getFilters().stream().allMatch(unFiltro -> unFiltro.matches(unPoi));
	}

	private void addResult(POI unPoi) {
		this.getResults().add(unPoi);
	}

	public void setConsulta(Consulta query) {
		this.consulta = query;
	}
	public Consulta getConsulta() {
		return this.consulta;
	}

	public void addFilter(PoiFilter unFiltro) {
		this.filters.add(unFiltro);
	}

	public void addRepository(RepositorioAbstracto unRepositorio) {
		this.repositories.add(unRepositorio);
	}
	public List<PoiFilter> getFilters() {
		return filters;
	}
	public List<RepositorioAbstracto> getRepositories() {
		return repositories;
	}

	public List<POI> getResults() {
		return results;
	}
}
