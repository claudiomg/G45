package poi.acciones;

import java.time.LocalDateTime;

import poi.modelo.puntoDeInteres.POI;
import poi.modelo.puntoDeInteres.ParadaColectivo;

public class EstaDisponible implements Accion {
	public boolean estaHabilitada;
	private LocalDateTime fechaYHora;
	private POI poiVerDisponibilidad;
	public boolean estaDisponible;
	
	
	public EstaDisponible(POI poi,LocalDateTime unaFechaHora){
		this.poiVerDisponibilidad = poi;
		this.fechaYHora = unaFechaHora;
	}

	@Override
	public String getNombreAccion(){
		return "EstaDisponible";
	}
	
	@Override
	public void ejecutarAccion() {
		if(poiVerDisponibilidad.getClass().equals(ParadaColectivo.class )){
			estaDisponible = true; //Los colectivos siempre estan disponibles
		}
		else{
			if (poiVerDisponibilidad.horariosCambiados.stream().anyMatch(unHorario -> unHorario.diaDisponible(fechaYHora))){
				estaDisponible =  poiVerDisponibilidad.horariosCambiados.stream().anyMatch(unHorario -> unHorario.estaDisponible(fechaYHora));
			}
			else
				estaDisponible =  poiVerDisponibilidad.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(fechaYHora, poiVerDisponibilidad.feriados))
						 && poiVerDisponibilidad.feriados.noEsUnFeriado(fechaYHora);

		}	
	}

	@Override
	public void habilitar() {
		this.estaHabilitada = true;
	}

	@Override
	public void deshabilitar() {
		this.estaHabilitada = false;
	} 
	
	public boolean getDisponibilidad(){
		return estaDisponible;
	}
}
