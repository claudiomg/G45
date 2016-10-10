package poi.utilidades;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements
		AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime date) {
		return Timestamp.valueOf(date);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp date) {
		return date.toLocalDateTime();
	}

}
