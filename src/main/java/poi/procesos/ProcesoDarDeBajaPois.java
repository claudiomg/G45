package poi.procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.finders.FilterByTag;
import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.DarDeBaja;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Administrador;
import poi.modelo.usuario.Terminal;
import poi.repositorios.RepositorioPOIsADarDeBaja;
import poi.utilidades.Consulta;

public class ProcesoDarDeBajaPois {
	
	public ProcesoDarDeBajaPois(){
		
	}
	
	
	RepositorioPOIsADarDeBaja repoDeBaja; 
	List<DarDeBaja> POIConRegisstrosParaDarDeBaja = new ArrayList<DarDeBaja>();
	PoiFinder poiFinder;
	Administrador admin;
	
	public Administrador getAdmin() {
		return admin;
	}


	public RepositorioPOIsADarDeBaja getRepoDeBaja() {
		return repoDeBaja;
	}


	public void setRepoDeBaja(RepositorioPOIsADarDeBaja repoDeBaja) {
		this.repoDeBaja = repoDeBaja;
	}


	public List<DarDeBaja> getPOIConRegisstrosParaDarDeBaja() {
		return POIConRegisstrosParaDarDeBaja;
	}


	public void setPOIConRegisstrosParaDarDeBaja(List<DarDeBaja> pOIConRegisstrosParaDarDeBaja) {
		POIConRegisstrosParaDarDeBaja = pOIConRegisstrosParaDarDeBaja;
	}


	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}


	public void correrProceso() throws Exception{
		try {
	    repoDeBaja.cleanRepository();
		repoDeBaja.actualizarRepositorio();}
		catch (Exception e){
			   System.out.println("Error de datos del servicio rest");
			}	
		
		List<Integer> idsInt = repoDeBaja.sacarLosIdsDeLosRegistros(repoDeBaja.obtenerRegistros());
		List<String> ids = this.convertirListaDeIntAListaDeStrings(idsInt);
		List<POI> listaDePoisADarDeBaja = this.filtrarPoisPorListaDePalabras(ids);
		int a = 0;
	    while(a != listaDePoisADarDeBaja.size()){
			   admin.removerPOI(listaDePoisADarDeBaja.get(a));
			   a++;
		   }
		}
	
        	
    public PoiFinder getPoiFinder() {
		return poiFinder;
	}


	public void setPoiFinder(PoiFinder poiFinder) {
		this.poiFinder = poiFinder;
	}


	public List<String> convertirListaDeIntAListaDeStrings(List<Integer> lista){
    	List<String >listaDeString = new ArrayList<String>();
    	listaDeString = lista.stream().map(elem ->String.valueOf(elem)).collect(Collectors.toList());
    	return listaDeString;
    	}
    
   public List<POI> filtrarPOIsPorPalabra(String palabra){
	    Terminal usuario = null;
		Consulta consulta = new Consulta(usuario,palabra);
		poiFinder.setConsulta(consulta);
	    poiFinder.addFilter(new FilterByTag(palabra));
		poiFinder.search();
		return poiFinder.getResults();
		}
   
   public List<POI> filtrarPoisPorListaDePalabras(List<String> listaDePalabras){
	   List<List<POI>> lista = listaDePalabras.stream().
			   				   map(palabra -> this.filtrarPOIsPorPalabra(palabra)).
			   				   collect(Collectors.toList());
	   return this.devolverLista(lista);
	   
   }
   
   public List<POI> devolverLista(List<List<POI>> listaConMasListas){
	   List<POI> lista = new ArrayList<POI>(); 
	   int i = 0;
	   while(i != listaConMasListas.size()){
		   lista.addAll(listaConMasListas.get(i));
		   i++;
	   }
	   return lista; 
   }
}
