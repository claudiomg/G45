package poi.repositorios;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.TimeRange;
import poi.servicioExternoCGP.CentroDTO;
import poi.servicioExternoCGP.ComponenteExterno;
import poi.servicioExternoCGP.RangoServicioDTO;
import poi.servicioExternoCGP.ServicioDTO;
import poi.utilidades.Posicion;

public class RepositorioCGPExternos extends RepositorioAbstracto {
	
	//All GameActions are singletons
	public static RepositorioAbstracto getInstance() {
		if(instance == null) {
			instance = new RepositorioCGPExternos();
		}
		return instance;
	}
	protected RepositorioCGPExternos() { /*Existe para anular la instanciacion*/ };
	
	public void actualizarRepositorio(){
		this.limpiarPOIs();
		ComponenteExterno componenteExterno = ComponenteExterno.getInstance();
		for ( CentroDTO dto : componenteExterno.getCgps()) {
			CGP newCGP = this.createCGPfrom(dto);
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
}
