package poi.repositorios;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.TimeRange;
import poi.servicioExternoCGP.CentroDTO;
import poi.servicioExternoCGP.ComponenteExterno;
import poi.servicioExternoCGP.RangoServicioDTO;
import poi.servicioExternoCGP.ServicioDTO;
import poi.utilidades.Posicion;

public class RepositorioCGPExternos implements Repositorio {
	private static RepositorioCGPExternos instance = new RepositorioCGPExternos();
	private List<CGP> CGPs = new ArrayList<CGP>();
	private List<POI> Pois = new ArrayList<POI>();
	public static RepositorioCGPExternos getInstance(){
		return instance;
	}
	
	public List<CGP> getCGPs() {
		return CGPs;
	}
	public List<POI> getPois(){
		return Pois;
	}
	public void actualizarRepositorio(){
		this.inicializarRepositorio();
		ComponenteExterno componenteExterno = ComponenteExterno.getInstance();
		for ( CentroDTO dto : componenteExterno.getCgps()) {
			CGP newCGP = this.createCGPfrom(dto);
			this.agregarCGP(newCGP);
			this.agregarPOI(newCGP);
		}
	}

	private CGP createCGPfrom(CentroDTO dto) {
		List<String> etiquetasCGP = new ArrayList<String>();
		etiquetasCGP.add(Integer.toString(dto.getNumeroComuna()));
		etiquetasCGP.add(dto.getZonasIncluidas());
		etiquetasCGP.add(dto.getNombreDirector());
		etiquetasCGP.add(dto.getDomicilio());
		etiquetasCGP.add(dto.getTelefono());
		List<Posicion> vertices = new ArrayList<Posicion>();
		CGP unCGP = new CGP(etiquetasCGP,null,vertices);
		for ( ServicioDTO unServicioDTO : dto.getServicios() ){
			unCGP.agregarServicio(this.createServiceFrom(unServicioDTO));
		}
		return unCGP;
	}

	private ServicioDeCGP createServiceFrom(ServicioDTO unServicioDTO) {
		ServicioDeCGP servicioDeCGP = new ServicioDeCGP();
		servicioDeCGP.setNombre(unServicioDTO.getNombre());
		for ( RangoServicioDTO rangoDTO : unServicioDTO.getRangosDiasServicio()){
			TimeRange rango = new TimeRange(
				LocalTime.of(rangoDTO.getHorarioDesde(),rangoDTO.getMinutosDesde(),0),
				LocalTime.of(rangoDTO.getHorarioHasta(),rangoDTO.getMinutosHasta(),0)
			);
			DayOfWeek diaDeSemana = DayOfWeek.of(rangoDTO.getNumeroDia());
			DisponibilidadHoraria disponibilidadHoraria = new DisponibilidadHoraria (diaDeSemana);
			disponibilidadHoraria.agregarNuevoRango(rango);
			servicioDeCGP.agregarDisponibilidadDeAtencion(disponibilidadHoraria);
		}
		return servicioDeCGP;
	}

	private void agregarCGP(CGP cgp) {
		CGPs.add(cgp);
	}

	private void inicializarRepositorio() {
		this.CGPs = new ArrayList<CGP>();		
	}
	
	private void agregarPOI(POI pois){
		Pois.add(pois);
	}
	
}
