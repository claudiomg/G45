package poi.repositorios;

import poi.modelo.puntoDeInteres.CGP;
import poi.servicioExternoCGP.AdaptadorCGP;
import poi.servicioExternoCGP.CentroDTO;
import poi.servicioExternoCGP.ComponenteExterno;

public class RepositorioCGPExternos extends RepositorioAbstracto {
	
	AdaptadorCGP adaptadorCGP = AdaptadorCGP.getInstance();
	
	//All GameActions are singletons
	public static RepositorioAbstracto getInstance() {
		if(instance == null) {
			instance = new RepositorioCGPExternos();
		}
		return instance;
	}
	protected RepositorioCGPExternos() { /*Existe para anular la instanciacion*/ };
	
	public void actualizarRepositorio(){
		this.limpiarPOIs();
		ComponenteExterno componenteExterno = ComponenteExterno.getInstance();
		for ( CentroDTO dto : componenteExterno.getCgps()) {
			CGP newCGP = adaptadorCGP.createCGPfrom(dto);
			//CGP newCGP = this.createCGPfrom(dto);
			this.agregarPOI(newCGP);
		}
	}

}
