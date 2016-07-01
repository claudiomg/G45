package poi.acciones;



import poi.modelo.puntoDeInteres.POI;
import poi.utilidades.Calculo;
import poi.utilidades.Posicion;

public class EstaCerca implements Accion {
	public boolean estaHabilitada;
	private POI poiVerCercania;
	private Posicion posicionUsuario;
	public boolean estaCerca;

	public EstaCerca(POI poi, Posicion posicion) {
		this.poiVerCercania = poi;
		this.posicionUsuario = posicion;
		estaHabilitada = true; // Habilitada por defecto
	}
	
	@Override
	public void habilitar() {
		this.estaHabilitada = true;
		
	}

	@Override
	public void deshabilitar() {
		this.estaHabilitada = false;
		
	}

	@Override
	public String getNombreAccion() {
		return "EstaCercaDe";
	}

	@Override
	public void ejecutarAccion() {
		if (estaHabilitada) {
			double distancia = Calculo.distanciaLineal(poiVerCercania.posicion, posicionUsuario);
			double distanciaLineal = Calculo.distanciaLineal(poiVerCercania.posicion, posicionUsuario);
			estaCerca = distancia < 0.5 && distanciaLineal < 0.5;
		}
		else {
			System.out.println("Esta accion fue inhabilitada");
		}
		
	}
	
	public boolean getCercania() {
		return estaCerca;
	}

}
