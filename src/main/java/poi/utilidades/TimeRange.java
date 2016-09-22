package poi.utilidades;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@Entity
@Table(name = "TimeRange")
public class TimeRange  implements WithGlobalEntityManager {
	
	@Id
	@GeneratedValue
	@Column(name = "timeRangeId")
	private Long timeRangeId;
	
	@Column(name = "endTime")
	private LocalTime endTime;
	
	@Column(name = "comienzoProceso")
	private LocalTime startTime;
	
	public TimeRange(){};
	
	public TimeRange(LocalTime start, LocalTime end) {
		this.startTime = start;
		this.endTime = end;
	}

	public boolean isValidValue(LocalTime unaHora) {
		return ((this.getStartTime().isBefore(unaHora) || this.getStartTime().equals(unaHora)) 
				&& (this.getEndTime().isAfter(unaHora) || this.getEndTime().equals(unaHora)));
	}

	private LocalTime getEndTime() {
		return this.endTime;
	}

	private LocalTime getStartTime() {
		return this.startTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void persistir(TimeRange time){
		entityManager().getTransaction().begin();
		entityManager().persist(time);
		entityManager().getTransaction().commit();
	}	

}
