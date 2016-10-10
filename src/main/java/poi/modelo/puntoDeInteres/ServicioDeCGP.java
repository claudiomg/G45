package poi.modelo.puntoDeInteres;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import poi.utilidades.DisponibilidadHoraria;
import poi.utilidades.ExcepcionHorarioCambiado;
import poi.utilidades.ExcepcionSinAtencion;

@Entity
@Table (name = "ServiciosDeCGP")
public class ServicioDeCGP {
	
	@Id
	@GeneratedValue
	@Column (name = "ServicioCGPId")
	private Long ServicioCGPId;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "ServicioCGPId")
	private ArrayList<DisponibilidadHoraria> disponibilidadesDeAtencion = new ArrayList<DisponibilidadHoraria>();
	
	@OneToOne
	@JoinColumn (name = "ExcepcionSinAtencionId")
    private ExcepcionSinAtencion feriados;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "ServicioCGPId")
    private ArrayList<ExcepcionHorarioCambiado> horariosCambiados = new ArrayList<ExcepcionHorarioCambiado>();
	
	@Column (name = "Nombre")
	private String nombre;
	
	public List<DisponibilidadHoraria> getDisponibilidadHoraria(){
		return this.disponibilidadesDeAtencion;
		
	}
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	public String getNombre(){
		return this.nombre;
	}
	
	public void setFeriados (ExcepcionSinAtencion feriados){
		this.feriados= feriados;
		
	}

	public boolean estaDisponible(LocalDateTime unaFechaHora) {
		boolean horarioCambiado = 
				this.horariosCambiados.stream().anyMatch(unHorario -> unHorario.diaDisponible(unaFechaHora));
			boolean horarioNormal =
				this.disponibilidadesDeAtencion.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora, this.feriados));
			boolean feriados = this.feriados.noEsUnFeriado(unaFechaHora);
			
			if (horarioCambiado){
				boolean disponibilidadHorarioCambiado = this.horariosCambiados.stream().anyMatch(unHorario -> unHorario.estaDisponible(unaFechaHora));
				return disponibilidadHorarioCambiado;
			} else{
				return  horarioNormal && feriados;
			}
	}

	public void agregarDisponibilidadDeAtencion(DisponibilidadHoraria disponibilidadHoraria) {
		this.disponibilidadesDeAtencion.add(disponibilidadHoraria);
	}
	
}
