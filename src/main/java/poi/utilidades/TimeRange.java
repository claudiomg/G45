package poi.utilidades;

import java.time.LocalTime;

public class TimeRange {

	private LocalTime endTime;
	private LocalTime startTime;

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

}
