package poi.servicioExternoCGP;

import java.util.ArrayList;
import java.util.List;

public class ComponenteExterno {

	private static ComponenteExterno instance = new ComponenteExterno();
	private List<CentroDTO> cgps = new ArrayList<CentroDTO>();

	private ComponenteExterno(){		
	}
	
	public static ComponenteExterno getInstance(){
		return instance;
	}
	
	public List<CentroDTO> getCgps() {
		return cgps;
	}

	public void setCgps(List<CentroDTO> cgps) {
		this.cgps = cgps;
	}
	
	public void agregarCentroDTO(CentroDTO centro){
		cgps.add(centro);
	}
}
