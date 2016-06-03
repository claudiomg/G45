package poi.ServicioExternoCGP;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import poi.servicioExternoCGP.CentroDTO;
import poi.servicioExternoCGP.ComponenteExterno;
import poi.servicioExternoCGP.RangoServicioDTO;
import poi.servicioExternoCGP.ServicioDTO;

public class ComponenteExternoTest {

	RangoServicioDTO rangoLunesRentas = new RangoServicioDTO(1, 8, 0, 18, 0);
	RangoServicioDTO rangoMartesRentas = new RangoServicioDTO(2, 8, 0, 18, 0);
	RangoServicioDTO rangoMiercolesRentas = new RangoServicioDTO(3, 8, 0, 18, 0);
	RangoServicioDTO rangoJuevesRentas = new RangoServicioDTO(4, 8, 0, 18, 0);
	RangoServicioDTO rangoViernesRentas = new RangoServicioDTO(5, 10, 0, 16, 0);
	RangoServicioDTO rangoLunesAsesoriaLegal = new RangoServicioDTO(1, 10, 0, 14, 0);
	RangoServicioDTO rangoMiercolesAsesoriaLegal = new RangoServicioDTO(3, 10, 0, 14, 0);
	RangoServicioDTO rangoViernesAsesoriaLegal = new RangoServicioDTO(5, 10, 0, 16, 0);	
	RangoServicioDTO rangoLunesAtencionCiudadana = new RangoServicioDTO(1, 10, 0, 17, 0);
	RangoServicioDTO rangoMartesAtencionCiudadana = new RangoServicioDTO(2, 11, 0, 18, 0);
	RangoServicioDTO rangoMiercolesAtencionCiudadana = new RangoServicioDTO(3, 9, 0, 18, 0);
	RangoServicioDTO rangoJuevesAtencionCiudadana = new RangoServicioDTO(4, 8, 0, 12, 0);
	RangoServicioDTO rangoViernesAtencionCiudadana = new RangoServicioDTO(5, 9, 0, 16, 0);
	List<RangoServicioDTO> listaRangosRentas = new ArrayList<RangoServicioDTO>();
	List<RangoServicioDTO> listaRangosAsesoriaLegal = new ArrayList<RangoServicioDTO>();	
	List<RangoServicioDTO> listaRangosAtencionCiudadana = new ArrayList<RangoServicioDTO>();		
	List<ServicioDTO> listaServicios1 = new ArrayList<ServicioDTO>();
	List<ServicioDTO> listaServicios2 = new ArrayList<ServicioDTO>();
	List<ServicioDTO> listaServicios3 = new ArrayList<ServicioDTO>();
			
	@Before
	public void inicializarEscenario(){
		listaRangosRentas.add(rangoLunesRentas);
		listaRangosRentas.add(rangoMartesRentas);
		listaRangosRentas.add(rangoMiercolesRentas);
		listaRangosRentas.add(rangoJuevesRentas);
		listaRangosRentas.add(rangoViernesRentas);
		listaRangosAsesoriaLegal.add(rangoLunesAsesoriaLegal);
		listaRangosAsesoriaLegal.add(rangoMiercolesAsesoriaLegal);
		listaRangosAsesoriaLegal.add(rangoViernesAsesoriaLegal);
		listaRangosAtencionCiudadana.add(rangoLunesAtencionCiudadana);
		listaRangosAtencionCiudadana.add(rangoMartesAtencionCiudadana);
		listaRangosAtencionCiudadana.add(rangoMiercolesAtencionCiudadana);
		listaRangosAtencionCiudadana.add(rangoJuevesAtencionCiudadana);
		listaRangosAtencionCiudadana.add(rangoViernesAtencionCiudadana);
		ServicioDTO rentas = new ServicioDTO("Rentas", listaRangosRentas);
		ServicioDTO asesoriaLegal = new ServicioDTO("Asesoria Legal", listaRangosAsesoriaLegal);
		ServicioDTO atencionCiudadana = new ServicioDTO("Atencion Ciudadana", listaRangosAtencionCiudadana);
		listaServicios1.add(rentas);
		listaServicios1.add(asesoriaLegal);
		listaServicios1.add(atencionCiudadana);
		listaServicios2.add(atencionCiudadana);
		listaServicios2.add(asesoriaLegal);
		listaServicios3.add(rentas);
		listaServicios3.add(atencionCiudadana);
	}	

	@Test
	public void usuarioSobreLaLineaDeBordeDeCoberturaDelCGP(){ //Sobre borde izquierdo del poligono	
		CentroDTO centro1 = 
				new CentroDTO(1, "Balvanera, San Cristobal", "Jose Perez", "Paraguay 203", "4356-4345", listaServicios1);
		CentroDTO centro2 =
				new CentroDTO(2, "Abasto", "Ariel Ortega", "Larrea 21", "4189-3232", listaServicios2);
		CentroDTO centro3 =
				new CentroDTO(3, "Colegiales, Belgrano", "Jose Sand", "Moldes 2452", "4222-3234", listaServicios3);		
		ComponenteExterno componenteExterno = ComponenteExterno.getInstance();
		componenteExterno.agregarCentroDTO(centro1);
		componenteExterno.agregarCentroDTO(centro2);
		componenteExterno.agregarCentroDTO(centro3);
		Assert.assertTrue(componenteExterno.getCgps().size() == 3);		
	}	

}
