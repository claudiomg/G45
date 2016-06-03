package poi.utilidades;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioPOI;
import poi.modelo.puntoDeInteres.POI;
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
	private List<SucursalBanco> bancosExternosFiltradosPorNombre = new ArrayList<SucursalBanco>();
	private List<SucursalBanco> bancosDeRepositoriosDePOIFiltradoPorNombre = new ArrayList<SucursalBanco>();
	
	
	public List<SucursalBanco> busquedaDeBancosExternosPorNombreYServicio(String nombre,String servicio){
		
		this.bancosExternosFiltradosPorNombre = this.busquedaDeBancosPorNombre(repositorioBancoExterno.getBancos(),nombre);
		return busquedaBancosPorServicio(bancosExternosFiltradosPorNombre,servicio);
	
	}
	
     public List<SucursalBanco> busquedaDeBancoEnRepositorioPOIPorNombreYServicio(String nombre,String servicio){
		
		this.bancosDeRepositoriosDePOIFiltradoPorNombre = this.busquedaDeBancosPorNombre(repositorioDePois.listaDeBancos,nombre);
		return busquedaBancosPorServicio(bancosDeRepositoriosDePOIFiltradoPorNombre,servicio);
	
		
	}
	
	
     
	public List<SucursalBanco> busquedaDeBancosPorNombre(List<SucursalBanco> repoDeBancos,String nombreBanco){
           
		return (repoDeBancos.stream().filter(unBanco -> (unBanco.getNombre() == nombreBanco))).collect(Collectors.toCollection(ArrayList::new));
		}
	
	public List<SucursalBanco> busquedaBancosPorServicio(List<SucursalBanco> lista,String servicio){
		
	   return	(lista.stream().filter(unBanco -> unBanco.haceEsteServicio(servicio))).collect(Collectors.toCollection(ArrayList::new));
		
	}
	
	
}