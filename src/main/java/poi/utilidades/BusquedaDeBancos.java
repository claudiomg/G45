package poi.utilidades;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioPOI;
import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.repositorios.RepositorioCGPExternos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BusquedaDeBancos {
	
	private RepositorioPOI repositorioDePois;
	private RepositorioBancosExternos repositorioBancoExterno;
	private RepositorioCGPExternos repositorioCGPExternos;
	private List<SucursalBanco> bancosFiltradosPorNombre = new ArrayList<SucursalBanco>();
	private List<SucursalBanco> bancosDeRepositoriosDePOIFiltradoPorNombre = new ArrayList<SucursalBanco>();
	
	

	
    
	public List<SucursalBanco> busquedaDeBancosPorNombreYServicio(List<SucursalBanco> repoDeBancos,String nombreBanco,String Servicio){
    	 bancosFiltradosPorNombre = this.busquedaDeBancosPorNombre(repoDeBancos, nombreBanco);
    	 return this.busquedaBancosPorServicio(bancosFiltradosPorNombre, Servicio);
    	 
     }
	
	
     
	public List<SucursalBanco> busquedaDeBancosPorNombre(List<SucursalBanco> repoDeBancos,String nombreBanco){ 
		return repoDeBancos
			.stream()
			.filter(unBanco -> (unBanco.getNombre() == nombreBanco))
			.collect(Collectors.toList());
	}
	
	public List<SucursalBanco> busquedaBancosPorServicio(List<SucursalBanco> lista,String servicio){
		
	   return lista
			.stream()
			.filter(unBanco -> unBanco.haceEsteServicio(servicio))
			.collect(Collectors.toList());
	}
	
	
}