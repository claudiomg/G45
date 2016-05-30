package poi.utilidades;

public class RangoServicioDTO {
	private int numeroDia;
	private int horarioDesde;
	private int minutosDesde;
	private int horarioHasta;
	private int minutosHasta;
	
	public RangoServicioDTO(int numeroDia, int horarioDesde, int minutosDesde, int horarioHasta, int minutosHasta) {
		this.numeroDia = numeroDia;
		this.horarioDesde = horarioDesde;
		this.minutosDesde = minutosDesde;
		this.horarioHasta = horarioHasta;
		this.minutosHasta = minutosHasta;
	}
	
	public int getNumeroDia() {
		return numeroDia;
	}
	
	public void setNumeroDia(int numeroDia) {
		this.numeroDia = numeroDia;
	}
	
	public int getHorarioDesde() {
		return horarioDesde;
	}
	
	public void setHorarioDesde(int horarioDesde) {
		this.horarioDesde = horarioDesde;
	}
	
	public int getMinutosDesde() {
		return minutosDesde;
	}
	
	public void setMinutosDesde(int minutosDesde) {
		this.minutosDesde = minutosDesde;
	}
	
	public int getHorarioHasta() {
		return horarioHasta;
	}
	
	public void setHorarioHasta(int horarioHasta) {
		this.horarioHasta = horarioHasta;
	}
	
	public int getMinutosHasta() {
		return minutosHasta;
	}
	
	public void setMinutosHasta(int minutosHasta) {
		this.minutosHasta = minutosHasta;
	}
	
}
