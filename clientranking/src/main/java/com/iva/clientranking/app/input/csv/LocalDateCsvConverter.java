package com.iva.clientranking.app.input.csv;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class LocalDateCsvConverter<T> extends AbstractBeanField<T> {

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
	private DateTimeFormatter formatter;

	public LocalDateCsvConverter() {
		super();
		this.formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	}

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		return ZonedDateTime.parse(value, formatter);
	}

	@Override
	protected String convertToWrite(Object value) throws CsvDataTypeMismatchException {
		String result = "";
		try {
			if (value != null) {
				ZonedDateTime zonedDateTime = (ZonedDateTime) value;
				result =zonedDateTime.format(formatter);
			}
		} catch (ClassCastException e) {
			CsvDataTypeMismatchException csve = new CsvDataTypeMismatchException(
					ResourceBundle.getBundle("convertGermanToBoolean", errorLocale).getString("field.not.boolean"));
			csve.initCause(e);
			throw csve;
		}
		return result;
	}

}
