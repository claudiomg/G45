package poi.finders;

import java.util.ArrayList;
import java.util.List;

import poi.repositorios.RepositorioAbstracto;
import poi.utilidades.Consulta;

public class PoiFinder {

	private Consulta consulta;
	private List<PoiFilter> filters = new ArrayList<PoiFilter>();
	private List<RepositorioAbstracto> repositories = new ArrayList<RepositorioAbstracto>();

	public void search() {
		// TODO Auto-generated method stub
		
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

}
