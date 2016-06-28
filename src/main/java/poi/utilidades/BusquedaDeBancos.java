package poi.utilidades;
import poi.repositorios.RepositorioAbstracto;
import poi.repositorios.RepositorioBancosExternos;
import poi.repositorios.RepositorioPOI;
import poi.modelo.puntoDeInteres.SucursalBanco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BusquedaDeBancos {
	
	public BusquedaDeBancos(){
		
	}
	
	private RepositorioAbstracto repositorioDePois;
	private RepositorioAbstracto repositorioBancoExterno;
	private List<SucursalBanco> bancosFiltradosPorNombre = new ArrayList<SucursalBanco>();
	
	
	public List<SucursalBanco> busquedaDeBancos(String nombreBanco,String servicio){
		List<SucursalBanco> bancosRepositorioDePoisFiltrado = new ArrayList<SucursalBanco>();
		List<SucursalBanco> bancosExternosFiltrado = new ArrayList<SucursalBanco>();
		bancosRepositorioDePoisFiltrado = busquedaDeBancosPorNombreYServicio(repositorioDePois.getBancos(),nombreBanco,servicio);
		bancosExternosFiltrado = busquedaDeBancosPorNombreYServicio(repositorioBancoExterno.getBancos(),nombreBanco,servicio);
		return this.concatenarLista(bancosExternosFiltrado,bancosRepositorioDePoisFiltrado);
	}
	
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
	
	public List<SucursalBanco> concatenarLista(List<SucursalBanco> repoDeBancos,List<SucursalBanco> repoDeBancos2){
		List<SucursalBanco> newList = new ArrayList<SucursalBanco>();
		newList.addAll(repoDeBancos);
		newList.addAll(repoDeBancos2);
		return newList;
		
		}

	public RepositorioAbstracto getRepositorioDePois() {
		return repositorioDePois;
	}

	public void setRepositorioDePois(RepositorioAbstracto repositorioDePois) {
		this.repositorioDePois = repositorioDePois;
	}

	public RepositorioAbstracto getRepositorioBancoExterno() {
		return repositorioBancoExterno;
	}

	public void setRepositorioBancoExterno(RepositorioAbstracto repositorioBancoExterno) {
		this.repositorioBancoExterno = repositorioBancoExterno;
	}
	
}