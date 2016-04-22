package poi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import G45.Utilidades.TimeRange;
import calculo.Calculo;
import usuario.Posicion;

public class LocalComercial extends POI{

	private TipoDeLocalComercial tipo;
	private ArrayList<DayOfWeek> diasDeAtencion = new ArrayList<DayOfWeek>();
	private ArrayList<TimeRange> horariosDeAtencion = new ArrayList<TimeRange>();
	
	public LocalComercial(TipoDeLocalComercial tipoDeLocal){
		this.tipo = tipoDeLocal;
	}
	
	public boolean estaCercaDe(Posicion posicionUsuario){
		double distancia = Calculo.distanciaEnKilometros(this.posicion, posicionUsuario);
		return distancia < this.tipo.getRadioDeCercania();
	}

	public void agregarDiaDeAtencion(DayOfWeek unDia) {
		this.diasDeAtencion.add(unDia);
	}

	public void agregarHorarioDeAtencion(TimeRange unRango) {
		this.horariosDeAtencion.add(unRango);
	}
	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		// verifico tanto el dia como la hora
		
		return this.esUnDiaDisponible(unaFechaHora) && this.esUnaHoraDisponible(unaFechaHora);
	}

	private boolean esUnaHoraDisponible(LocalDateTime unaFechaHora) {
		LocalTime horaActual = LocalTime.of(
				unaFechaHora.getHour(),
				unaFechaHora.getMinute(),
				unaFechaHora.getSecond()
				);
		
		return (this.getHorariosDeAtencion().stream().anyMatch(
	            range -> range.isValidValue(horaActual)));
	}

	private ArrayList<TimeRange> getHorariosDeAtencion() {
		return this.horariosDeAtencion;
	}

	private boolean esUnDiaDisponible(LocalDateTime unaFechaHora) {
		return this.getDiasDeAtencion().contains(unaFechaHora.getDayOfWeek());
	}

	private ArrayList<DayOfWeek> getDiasDeAtencion() {
		return this.diasDeAtencion;
	}
	
}
