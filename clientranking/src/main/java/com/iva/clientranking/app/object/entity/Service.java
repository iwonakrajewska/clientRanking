package com.iva.clientranking.app.object.entity;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class Service implements CsvBean {

	@CsvBindByName(column = "id")
	private String id;
	@CsvBindByName(column = "appointment_id")
	private String appointmentId;
	@CsvBindByName(column = "name")
	private String name;
	@CsvBindByName(column = "price")
	private BigDecimal price;
	@CsvBindByName(column = "loyalty_points")
	private Long loyaltyPoints;

}
