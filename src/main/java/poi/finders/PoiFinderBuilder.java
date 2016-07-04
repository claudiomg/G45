package poi.finders;

import java.time.LocalDateTime;

import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioCGPExternos;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Consulta;
import poi.utilidades.Posicion;

public class PoiFinderBuilder {

	private RequestMediator request;
	private Terminal user;
	private String palabraBuscada;

	public PoiFinderBuilder(RequestMediator request) {
		this.setRequest(request);
	}
	
	public PoiFinder buildFinder() {
		// creo un finder, lo relaciono y lo devuelvo
		PoiFinder finder = new PoiFinder();
		
		this.setUser();
		this.setPalabraBuscada();
		this.setQueryOn(finder);
		this.setFiltersOn(finder);
		this.setRepositoriesOn(finder);
		
		return finder;
	}

	private void setRepositoriesOn(PoiFinder finder) {
		finder.addRepository(RepositorioPOI.getInstance());
		this.addBankRepository(finder);
		this.addCgpServiceRepository(finder);
	}

	private void addCgpServiceRepository(PoiFinder finder) {
		//se agrega solo si se habilito la busqueda en bancos
		boolean enable = this.getRequest().enabledSearchOfCgpService();
		if (enable){
			finder.addRepository(RepositorioCGPExternos.getInstance());
		}
	}

	private void addBankRepository(PoiFinder finder) {
		//se agrega solo si se habilito la busqueda en bancos
		boolean enable = this.getRequest().enabledSearchOfBank();
		if (enable){
			finder.addRepository(RepositorioBancosExternos.getInstance());
		}
	}

	private void setFiltersOn(PoiFinder finder) {
		//me encargo de agregar los filtros
		this.addTagFilterOn(finder);
		this.addDisponibilityFilterOn(finder);
		this.addProximityFilterOn(finder);
	}

	private void addProximityFilterOn(PoiFinder finder) {
		//dependo del request y el usuario
		boolean enableFilter = this.getRequest().enabledProximityFilter();
		boolean hasAccess = this.getUser().canFilterByProximity();
		if (enableFilter && hasAccess){
			Posicion unaPosicion = this.getRequest().getPositionForProximity();
			finder.addFilter(new FilterByProximity(unaPosicion));
		}
	}

	private void addDisponibilityFilterOn(PoiFinder finder) {
		//dependo del request y el usuario
		boolean enableFilter = this.getRequest().enabledDisponibilityFilter();
		boolean hasAccess = this.getUser().canFilterByDisponibility();
		if (enableFilter && hasAccess){
			LocalDateTime unaFechaHora = this.getRequest().getDateAndTimeForDisponibility();
			finder.addFilter(new FilterByDisponibility(unaFechaHora));
		}
	}

	private void addTagFilterOn(PoiFinder finder) {
		//dependo del request y el usuario
		boolean hasFilterValue = !this.getPalabraBuscada().isEmpty();
		boolean hasAccess = this.getUser().canFilterByTag();
		if (hasAccess && hasFilterValue){
			finder.addFilter(new FilterByTag(this.getPalabraBuscada()));
		}
	}

	private void setQueryOn(PoiFinder finder) {
		Consulta query = new Consulta(this.getUser(),this.getPalabraBuscada());
		//si hay mas atribubutos setearlos aca
		finder.setConsulta(query);
	}

	private void setPalabraBuscada() {
		this.palabraBuscada = this.getRequest().palabraBuscada();
	}
	private String getPalabraBuscada() {
		return this.palabraBuscada;
	}

	private void setUser() {
		this.user = this.getRequest().user();
	}
	private Terminal getUser() {
		return this.user;
	}

	private void setRequest(RequestMediator request) {
		this.request = request;
	}
	
	private RequestMediator getRequest() {
		return this.request;
	}

}
