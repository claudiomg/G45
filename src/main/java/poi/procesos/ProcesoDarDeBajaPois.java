package poi.procesos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import poi.finders.FilterByTag;
import poi.finders.PoiFinder;
import poi.modelo.puntoDeInteres.POI;
import poi.modelo.usuario.Administrador;
import poi.repositorios.RepositorioPOIsADarDeBaja;

public class ProcesoDarDeBajaPois {
	
	public ProcesoDarDeBajaPois(){
		
	}
	
	
	RepositorioPOIsADarDeBaja repoDeBaja = RepositorioPOIsADarDeBaja.getInstance();
	//List<DarDeBaja> POIConRegisstrosParaDarDeBaja = new ArrayList<DarDeBaja>();
	PoiFinder poiFinder = new PoiFinder();
	String nombre;
	Administrador admin = new Administrador(nombre);
	
	public Administrador getAdmin() {
		return admin;
	}


	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}


	public void correrProceso() throws Exception{
		repoDeBaja.cleanRepository();
		repoDeBaja.actualizarRepositorio();
		/*List<Integer> idsInt = repoDeBaja.sacarLosIdsDeLosRegistros(repoDeBaja.obtenerRegistros());
		List<String> ids = this.convertirListaDeIntAListaDeStrings(idsInt);
		List<POI> listaDePoisADarDeBaja = this.filtrarPoisPorListaDePalabras(ids);
		for(int i = 0;listaDePoisADarDeBaja.size() >= i;i++){
			admin.removerPOI(listaDePoisADarDeBaja.get(i));
			}*/
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
	   for(int i= 0; listaConMasListas.size() >= i; i++){
        	 lista.addAll(listaConMasListas.get(i));
        	}
         return lista;
   }
}
