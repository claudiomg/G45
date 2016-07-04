package poi.servicioExternoCGP;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.utilidades.Direccion;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class AdaptadorCGP {

	public static AdaptadorCGP instance = null;

	private AdaptadorCGP(){		
	};

	public static AdaptadorCGP getInstance() {
		if(instance == null) {
			instance = new AdaptadorCGP();
		}
		return instance;
	}

	public CGP createCGPfrom(CentroDTO dto) {
		List<String> etiquetasCGP = new ArrayList<String>();
		String nombre = dto.getNombre();
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(dto.getLatitud(), dto.getLongitud());
		etiquetasCGP.add(Integer.toString(dto.getNumeroComuna()));
		etiquetasCGP.add(dto.getZonasIncluidas());
		etiquetasCGP.add(dto.getNombreDirector());
		etiquetasCGP.add(dto.getDomicilio());
		etiquetasCGP.add(dto.getTelefono());
		List<Posicion> vertices = new ArrayList<Posicion>();
		CGP unCGP = new CGP(nombre,posicion,direccion,vertices);
		for ( ServicioDTO unServicioDTO : dto.getServicios() ){
			unCGP.agregarServicio(this.createServiceFrom(unServicioDTO));
		}
		return unCGP;
	}

	public ServicioDeCGP createServiceFrom(ServicioDTO unServicioDTO) {
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
