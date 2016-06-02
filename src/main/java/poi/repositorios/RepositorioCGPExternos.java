package poi.repositorios;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.servicioExternoCGP.CentroDTO;
import poi.servicioExternoCGP.ComponenteExterno;
import poi.servicioExternoCGP.RangoServicioDTO;
import poi.servicioExternoCGP.ServicioDTO;
import poi.utilidades.Posicion;

public class RepositorioCGPExternos {
	private static RepositorioCGPExternos instance = new RepositorioCGPExternos();
	private List<CGP> CGPs = new ArrayList<CGP>();
	
	public static RepositorioCGPExternos getInstance(){
		return instance;
	}
	
	public List<CGP> getCGPs() {
		return CGPs;
	}
	
	public void actualizarRepositorio(){
		this.inicializarRepositorio();
		ComponenteExterno componenteExterno = ComponenteExterno.getInstance();
		for ( CentroDTO dto : componenteExterno.getCgps()) {
			CGP newCGP = this.createCGPfrom(dto);
			this.agregarCGP(newCGP);
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
		//No estoy cargando el rango porque no lo permite,
		//tiene q permitir rangos por dia
		//TimeRange rango = new TimeRange(LocalTime.of(10,0,0),LocalTime.of(13,0,0));
		//servicioDeCGP.agregarHorarioDeAtencion(rango);
		for ( RangoServicioDTO rangoDTO : unServicioDTO.getRangosDiasServicio()){
			servicioDeCGP.agregarDiaDeAtencion(this.createDayFrom(rangoDTO.getNumeroDia()));
		}
		return servicioDeCGP;
	}

	private DayOfWeek createDayFrom(int numeroDia) {
		switch (numeroDia) {
	        case 1:  return DayOfWeek.MONDAY;
	        case 2:  return DayOfWeek.TUESDAY;
	        case 3:  return DayOfWeek.WEDNESDAY;
	        case 4:  return DayOfWeek.THURSDAY;
	        case 5:  return DayOfWeek.FRIDAY;
	        case 6:  return DayOfWeek.SATURDAY;
	        case 7:  return DayOfWeek.SUNDAY;
	    }
		return null;
	}

	private void agregarCGP(CGP cgp) {
		CGPs.add(cgp);
	}

	private void inicializarRepositorio() {
		this.CGPs = new ArrayList<CGP>();		
	}
	
}
