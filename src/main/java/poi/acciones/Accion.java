package poi.acciones;

public interface Accion {
	
	public void habilitar();
	public void deshabilitar();
	
	public String getNombreAccion();
	
	public void ejecutarAccion();
}
