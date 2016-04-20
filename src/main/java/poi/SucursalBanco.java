package poi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import calculo.Calculo;
import usuario.Posicion;

public class SucursalBanco extends POI{
	
	private ArrayList<DayOfWeek> diasDeAtencion = new ArrayList<DayOfWeek>();
	private LocalTime horaCierre;
	private LocalTime horaApertura;

	public SucursalBanco(List<String> etiquetas,Posicion posicion) {
		this.etiquetas = etiquetas;
		this.posicion = posicion;
		this.agregarDiaDeAtencion(DayOfWeek.MONDAY);
		this.agregarDiaDeAtencion(DayOfWeek.TUESDAY);
		this.agregarDiaDeAtencion(DayOfWeek.WEDNESDAY);
		this.agregarDiaDeAtencion(DayOfWeek.THURSDAY);
		this.agregarDiaDeAtencion(DayOfWeek.FRIDAY);
		this.setHoraApertura(LocalTime.of(10,0,0));
		this.setHoraCierre(LocalTime.of(15,0,0));
	}
	private void setHoraCierre(LocalTime hora) {
		this.horaCierre = hora;		
	}
	private void setHoraApertura(LocalTime hora) {
		this.horaApertura = hora;
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
		return ((this.getHoraApertura().isBefore(horaActual) || this.getHoraApertura().equals(horaActual)) 
				&& (this.getHoraCierre().isAfter(horaActual) || this.getHoraCierre().equals(horaActual)));
	}
	private LocalTime getHoraCierre() {
		return this.horaCierre;
	}
	private LocalTime getHoraApertura() {
		return this.horaApertura;
	}
	private boolean esUnDiaDisponible(LocalDateTime unaFechaHora) {
		return this.getDiasDeAtencion().contains(unaFechaHora.getDayOfWeek());
	}
	private ArrayList<DayOfWeek> getDiasDeAtencion() {
		return this.diasDeAtencion;
	}
}
