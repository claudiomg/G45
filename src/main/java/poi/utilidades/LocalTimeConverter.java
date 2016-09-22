package poi.utilidades;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

		@Override
		public Time convertToDatabaseColumn(LocalTime attribute) {			
			return attribute == null ? null : Time.valueOf(attribute);
		}

		@Override
		public LocalTime convertToEntityAttribute(Time date) {
			return date == null ? null : date.toLocalTime();
		}
}
