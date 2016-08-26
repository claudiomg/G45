package poi.procesos;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import poi.modelo.puntoDeInteres.LocalComercial;
import poi.modelo.puntoDeInteres.POI;
import poi.repositorios.RepositorioAbstractoPOI;
import poi.repositorios.RepositorioPOI;

public class ProcesoActualizacionLocales {

	String ruta = "C:/Users/Claudio/Documents/prueba.txt";

	public ProcesoActualizacionLocales() {
	}

	public void correrProceso(){
		List <String> lista = this.cargaLista();
		if (!lista.isEmpty()) {
			String nombre = lista.get(0);		
			List <String> palabrasClave = new ArrayList<String>();
			for (int i = 1; i <= (lista.size() - 1); i++ ){
				palabrasClave.add(lista.get(i));
			}
			if(existePOI(nombre)){
				cambiarEtiquetas(nombre, palabrasClave);
			} else {
				agregarPOI(nombre, palabrasClave);
			}
		}
	}
	
	public void cambiarEtiquetas(String nombre, List<String> etiquetas){
		RepositorioAbstractoPOI repositorioLocal = RepositorioPOI.getInstance();		
		repositorioLocal.getRegistros().stream()
			.filter(local -> ((local.getClass()==LocalComercial.class) && (local.getNombre().equals(nombre))))
			.findFirst().get().setPalabrasClave(etiquetas);
	}

	public void agregarPOI(String nombre, List<String> etiquetas){
		RepositorioAbstractoPOI repositorioLocal = RepositorioPOI.getInstance();		
		POI nuevoLocal = new LocalComercial(nombre, null, null, null);
		nuevoLocal.setPalabrasClave(etiquetas);
		repositorioLocal.agregarRegistro(nuevoLocal);
	}

	public boolean existePOI(String nombre){
		RepositorioAbstractoPOI repositorioLocal = RepositorioPOI.getInstance();
		return repositorioLocal.getRegistros().stream()
				.anyMatch(local -> ((local.getClass()==LocalComercial.class) && local.getNombre().equals(nombre)));
	}

	public List<String> cargaLista(){
		List <String> lista = new ArrayList<String>();
		try{
		   FileReader fr = new FileReader(ruta);
		   int caract = fr.read();
		   String nombre = "";
		   String etiqueta = "";
		   boolean nombreOK = false;
		   boolean etiquetaOK = false;
		   while(caract != -1){
			   while(nombreOK == false){
				   char caracter = (char)caract;			   
				   String prueba = Character.toString(caracter);			   
				   nombre += prueba;
				   caract = fr.read();
				   if (caract == 59){
					   nombreOK = true;
					   lista.add(nombre);
					   caract = fr.read();
				   }
			   }
			   while((caract != -1) && (etiquetaOK == false) && (caract != 32)){
				   char caracter = (char)caract;			   
				   String prueba = Character.toString(caracter);			   
				   etiqueta += prueba;
				   caract = fr.read();
				   if ((caract == 32) || (caract == -1)){
					   lista.add(etiqueta);
					   etiqueta = "";
					   etiquetaOK = true;
				   }
			   }
			   etiquetaOK = false;
			   caract = fr.read();
		   }
		   
		   fr.close();
		}
		catch (Exception e){
		   System.out.println("Error de lectura del archivo");
		}				
		return lista;
	}
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}

