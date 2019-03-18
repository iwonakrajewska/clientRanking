package com.iva.clientranking.app.input.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.opencsv.exceptions.CsvDataTypeMismatchException;


public class LocalDateCsvConverterTest {

	private LocalDateCsvConverter<String> localDateCsvConverter = new LocalDateCsvConverter<String>();

	@Test
	public void testDateConversion() throws CsvDataTypeMismatchException {
		String s1 = "2016-03-21 10:00:00 +0000";
		ZonedDateTime zdt = (ZonedDateTime)localDateCsvConverter.convert(s1);
		String result = localDateCsvConverter.convertToWrite(zdt);
		assertEquals(s1, result);
	}	
	
	@Test
	public void testZonedDateConversion() throws CsvDataTypeMismatchException {
		String s1 = "2016-03-21 10:00:00 +0100";
		ZonedDateTime zdt = (ZonedDateTime)localDateCsvConverter.convert(s1);
		String result = localDateCsvConverter.convertToWrite(zdt);
		assertEquals(s1, result);
	}
	
	@Test
	public void testEmptyString() throws CsvDataTypeMismatchException {
		String s1 = "";
		assertNull(localDateCsvConverter.convert(s1));
	}	
	
	@Test
	public void testEmptyDate() throws CsvDataTypeMismatchException {
		String result = localDateCsvConverter.convertToWrite(null);
		assertEquals("", result);
	}
	
	@Test(expected=CsvDataTypeMismatchException.class)
	public void testIncorrectDate() throws CsvDataTypeMismatchException {
		String result = localDateCsvConverter.convertToWrite(LocalDateTime.now());
		fail("Shouldn't be here");
	}
}
