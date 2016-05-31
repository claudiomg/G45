package poi.modelo.puntoDeInteres;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import poi.utilidades.Posicion;
import poi.utilidades.TimeRange;

public class SucursalBanco extends POI{
	
	private ArrayList<DayOfWeek> diasDeAtencion = new ArrayList<DayOfWeek>();
	private TimeRange horarioDeAtencion;
	private String nombre;
	private String sucursal;
	private String gerente;
	private List<String> servicios;
	

	public SucursalBanco(List<String> etiquetas,Posicion posicion) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
		this.agregarDiaDeAtencion(DayOfWeek.MONDAY);
		this.agregarDiaDeAtencion(DayOfWeek.TUESDAY);
		this.agregarDiaDeAtencion(DayOfWeek.WEDNESDAY);
		this.agregarDiaDeAtencion(DayOfWeek.THURSDAY);
		this.agregarDiaDeAtencion(DayOfWeek.FRIDAY);
		this.setHorarioDeAtencion(new TimeRange(LocalTime.of(10,0,0),LocalTime.of(15,00,0)));
	}
	private void setHorarioDeAtencion(TimeRange timeRange) {
		this.horarioDeAtencion = timeRange;
	}
	private void agregarDiaDeAtencion(DayOfWeek unDia) {
		this.diasDeAtencion.add(unDia);
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
		return (this.getHorarioDeAtencion().isValidValue(horaActual));
	}
	private TimeRange getHorarioDeAtencion() {
		return this.horarioDeAtencion;
	}
	private boolean esUnDiaDisponible(LocalDateTime unaFechaHora) {
		return this.getDiasDeAtencion().contains(unaFechaHora.getDayOfWeek());
	}
	private ArrayList<DayOfWeek> getDiasDeAtencion() {
		return this.diasDeAtencion;
	}
}
