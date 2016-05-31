package poi.servicioExternoCGP;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {
	
	private String nombre;
	private List<RangoServicioDTO> rangosDiasServicio = new ArrayList<RangoServicioDTO>();
	
	public ServicioDTO(String nombre, List<RangoServicioDTO> rangosDiasServicio) {
		this.nombre = nombre;
		this.rangosDiasServicio = rangosDiasServicio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<RangoServicioDTO> getRangosDiasServicio() {
		return rangosDiasServicio;
	}
	public void setRangosDiasServicio(List<RangoServicioDTO> rangosDiasServicio) {
		this.rangosDiasServicio = rangosDiasServicio;
	}
	
}
