package poi.repositorios;

import poi.modelo.puntoDeInteres.CGP;
import poi.servicioExternoCGP.AdaptadorCGP;
import poi.servicioExternoCGP.CentroDTO;
import poi.servicioExternoCGP.ComponenteExterno;

public class RepositorioCGPExternos extends RepositorioAbstracto {
	
	AdaptadorCGP adaptadorCGP = AdaptadorCGP.getInstance();
	protected static RepositorioCGPExternos instance;
	protected RepositorioCGPExternos() { /*Existe para anular la instanciacion*/ };
	public static RepositorioCGPExternos getInstance() {
		if(instance == null) {
			instance = new RepositorioCGPExternos();
		}
		return instance;
	}
	
	public void actualizarRepositorio(){
		this.cleanRepository();
		ComponenteExterno componenteExterno = ComponenteExterno.getInstance();
		for ( CentroDTO dto : componenteExterno.getCgps()) {
			CGP newCGP = adaptadorCGP.createCGPfrom(dto);
			//CGP newCGP = this.createCGPfrom(dto);
			this.agregarRegistro(newCGP);
		}
	}

}
