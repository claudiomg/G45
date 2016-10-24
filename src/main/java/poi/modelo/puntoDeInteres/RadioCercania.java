package poi.modelo.puntoDeInteres;

public enum RadioCercania {
	LibreriaEscolar(0.5),Kiosco(0.2);
	
	private Double radio;
	
	private RadioCercania(Double number){
		this.radio = number;
	}
	
	public Double getValue(){
		return this.radio;
	}
}