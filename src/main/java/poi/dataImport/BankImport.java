package poi.dataImport;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import poi.modelo.puntoDeInteres.SucursalBanco;
import poi.repositorios.RepositorioPOI;
import poi.utilidades.Direccion;
import poi.utilidades.Posicion;

public class BankImport extends DataImport {
	
	SucursalBanco sucursal;
	RepositorioPOI repositorioPOI = RepositorioPOI.getInstance();
	
	@Override
	void processData(String[] data) {
		
		this.createDireccion(data);
		this.createPosicion(data);
		this.createBanco(data);
		this.createServicios();
		repositorioPOI.agregarRegistro(sucursal);
	}

	private void createServicios() {
		List<String> servicios = Arrays.asList(
			"Depósitos",
			"Transacciones",
			"Préstamos",
			"Asesoramiento financiero",
			"Cambio de moneda extranjera",
			"Cajas de seguridad"
		);
		Random rnd= new Random();
		for (String serv: servicios){
			if (rnd.nextInt(10) % 2 == 0){
				sucursal.agregarServicio(serv);
			}
		}
	}

	private void createBanco(String[] data) {
		sucursal = new SucursalBanco(data[3],posicion,direccion);
		sucursal.setSucursal(data[7]);
	}

	private void createPosicion(String[] data) {
		String[] cord = data[0]
			.replace("POINT (", "")
			.replace(")", "")
			.split(" ");
		posicion = new Posicion(new Double(cord[0]), new Double(cord[1]));
	}

	private void createDireccion(String[] data) {
		direccion = new Direccion();
		direccion.setCalle(data[6]);
		//direccion.setNumero();
		//direccion.setPiso();
		//direccion.setDepartamento();
		//direccion.setLocalidad();
		//direccion.setCodigoPostal();
		//direccion.setEntreCalleDerecha();
		//direccion.setEntreCalleDerecha();
		direccion.setBarrio(data[7]);
		direccion.setLocalidad("CABA");
		direccion.setPais("Argentina");
		direccion.setProvincia("Buenos Aires");
	}

/*cordenada;0
 * ID_USIG;1
 * NRO_BANCO;2
 * NOMBRE;3
 * OBJETO;4
 * SUCURSAL;5
 * DIRECCION;6
 * BARRIO;7
 * COMUNA;8
 * FUNCION;9
 * TELEFONO;10
 * DETALLE;11
 * DIREC_NORM;12
 * DIREC_GIS;13*/
}
