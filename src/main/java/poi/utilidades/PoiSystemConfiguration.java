package poi.utilidades;

import java.time.Duration;

public class PoiSystemConfiguration {

	protected static PoiSystemConfiguration instance;
	protected PoiSystemConfiguration() { /*Existe para anular la instanciacion*/ };
	public static PoiSystemConfiguration getInstance() {
		if(instance == null) {
			instance = new PoiSystemConfiguration();
		}
		return instance;
	}
	
	private Duration tiempoProcesamientoMaximo = Duration.ofSeconds(10);
	
	public Duration getTiempoProcesamientoMaximo() {
		return tiempoProcesamientoMaximo;
	}
	public void setTiempoProcesamientoMaximo(Duration tiempoProcesamientoMaximo) {
		this.tiempoProcesamientoMaximo = tiempoProcesamientoMaximo;
	}
	
}
