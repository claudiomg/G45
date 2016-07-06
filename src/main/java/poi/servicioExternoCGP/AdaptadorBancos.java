package poi.servicioExternoCGP;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.CGP;
import poi.modelo.puntoDeInteres.ServicioDeCGP;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.utilidades.Direccion;
import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class AdaptadorBancos {

	public static AdaptadorBancos instance = null;

	private AdaptadorBancos(){		
	};

	public static AdaptadorBancos getInstance() {
		if(instance == null) {
			instance = new AdaptadorBancos();
		}
		return instance;
	}

	public SucursalBanco createCGPfrom(CentroDTO dto) {
		List<String> etiquetasCGP = new ArrayList<String>();
		String nombre = dto.getNombre();
		Direccion direccion = new Direccion();
		Posicion posicion = new Posicion(dto.getLatitud(), dto.getLongitud());
		etiquetasCGP.add(Integer.toString(dto.getNumeroComuna()));
		etiquetasCGP.add(dto.getZonasIncluidas());
		etiquetasCGP.add(dto.getNombreDirector());
		etiquetasCGP.add(dto.getDomicilio());
		etiquetasCGP.add(dto.getTelefono());
	
		SucursalBanco unbanco = new SucursalBanco(nombre,posicion,direccion);
		for ( ServicioDTO unServicioDTO : dto.getServicios() ){
			unbanco.agregarServicio(this.createSucursaleFrom(unServicioDTO).toString());
		}
		return unbanco;
	}

	public SucursalBanco createSucursaleFrom(ServicioDTO unServicioDTO) {
		SucursalBanco sucursal = new SucursalBanco(null, null, null);
		
		for ( RangoServicioDTO rangoDTO : unServicioDTO.getRangosDiasServicio()){
			TimeRange rango = new TimeRange(
				LocalTime.of(rangoDTO.getHorarioDesde(),rangoDTO.getMinutosDesde(),0),
				LocalTime.of(rangoDTO.getHorarioHasta(),rangoDTO.getMinutosHasta(),0)
			);
			DayOfWeek diaDeSemana = DayOfWeek.of(rangoDTO.getNumeroDia());
			DisponibilidadHoraria disponibilidadHoraria = new DisponibilidadHoraria (diaDeSemana);
			disponibilidadHoraria.agregarNuevoRango(rango);
			sucursal.addDisponibilidadDeAtencion(disponibilidadHoraria);
		}
		return sucursal;
	}
	
}