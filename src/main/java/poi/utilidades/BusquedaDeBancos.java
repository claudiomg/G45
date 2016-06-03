package poi.utilidades;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioPOI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.repositorios.RepositorioCGPExternos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BusquedaDeBancos {
	
	private RepositorioCGPExternos repositorioCGPExternos;
	private RepositorioPOI repositorioDePois;
	private RepositorioBancosExternos repositorioBancoExterno;
	private List<SucursalBanco> bancosFiltradosPorNombre = new ArrayList<SucursalBanco>();
	
	
	public List<SucursalBanco> busquedaDeBancosExternosPorNombreYServicio(String nombre,String servicio){
		
		this.bancosFiltradosPorNombre = this.busquedaDeBancoExternosPorNombre(nombre);
		
		return(bancosFiltradosPorNombre.stream().filter(unBanco -> unBanco.haceEsteServicio(servicio))).collect(Collectors.toCollection(ArrayList::new));
		
	
	}
	
	
	public List<SucursalBanco> busquedaDeBancoExternosPorNombre(String nombreBanco){

		return (repositorioBancoExterno.getBancos().stream().filter(unBanco -> (unBanco.getNombre() == nombreBanco))).collect(Collectors.toCollection(ArrayList::new));
	
	}
	
}