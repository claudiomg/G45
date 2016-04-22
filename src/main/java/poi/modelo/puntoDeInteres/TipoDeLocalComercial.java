package poi.modelo.puntoDeInteres;

public abstract class TipoDeLocalComercial implements Cloneable {
	
	protected double RADIO_CERCANIA;

	public TipoDeLocalComercial clone() throws CloneNotSupportedException {
		  return (TipoDeLocalComercial) super.clone();
	}
	
	public double getRadioDeCercania(){
		return RADIO_CERCANIA;
	}
	protected void setRadioDeCercania(double radio) {
		RADIO_CERCANIA = radio;
	}
}
