package com.iva.clientranking.app.object.entity;

import java.time.ZonedDateTime;

import com.iva.clientranking.app.input.csv.LocalDateCsvConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import lombok.Data;

@Data
public class Appointment implements CsvBean {

	@CsvBindByName(column = "id")
	private String id;
	@CsvBindByName(column = "client_id")
	private String clientId;
	@CsvCustomBindByName(column = "start_time", converter = LocalDateCsvConverter.class)
	private ZonedDateTime startTime;
	@CsvCustomBindByName(column = "end_time", converter = LocalDateCsvConverter.class)
	private ZonedDateTime endTime;

}
