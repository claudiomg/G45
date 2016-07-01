package poi.acciones;

import poi.utilidades.Consulta;

public class BuscarPorPalabra implements Accion{
	
	public boolean estaHabilitada;
	private Consulta consultaActiva;
	private String palabra;

	public BuscarPorPalabra (String palabra, Consulta consultaActiva) {
		this.palabra = palabra;
		this.consultaActiva = consultaActiva;
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
		return "BuscarPorPalabra";
	}

	@Override
	public void ejecutarAccion() {
		if (estaHabilitada) {
			this.consultaActiva.generarHistorial(palabra);
			this.consultaActiva.buscarPorPalabra(palabra);
			this.consultaActiva.buscarPorPalabraEnExterno(palabra);
		}
		else {
			System.out.println("Esta accion fue inhabilitada");
		}
		
	}

}
