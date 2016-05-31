package poi.repositorios;

import java.util.ArrayList;
import java.util.List;

import poi.modelo.puntoDeInteres.CGP;

public class RepositorioCGPExternos {
	private static RepositorioCGPExternos instance = new RepositorioCGPExternos();
	private List<CGP> CGPs = new ArrayList<CGP>();
	
	public static RepositorioCGPExternos getInstance(){
		return instance;
	}
	
	public List<CGP> getCGPs() {
		return CGPs;
	}
	
	public void actualizarRepositorio(){
		//TODO obtener CGPs de biblioteca externa
	}
	
}
