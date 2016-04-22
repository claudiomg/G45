package poi.modelo.puntoDeInteres;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import poi.utilidades.TimeRange;

public class ServicioDeCGP {

	private ArrayList<DayOfWeek> diasDeAtencion = new ArrayList<DayOfWeek>();
	private ArrayList<TimeRange> horariosDeAtencion = new ArrayList<TimeRange>();

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
