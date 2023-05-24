package bloodbank.xml.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

	@Override
	public LocalDate unmarshal(String dateString) throws Exception {
		return LocalDate.parse(dateString, DATE_FORMAT);
	}

	@Override
	public String marshal(LocalDate date) throws Exception {
		return date.format(DATE_FORMAT);
	}
}
