package com.iva.clientranking.app.object.entity;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class Client implements CsvBean {

	@CsvBindByName(column = "id")
	private String id;
	@CsvBindByName(column = "first_name")
	private String firstName;
	@CsvBindByName(column = "last_name")
	private String lastName;
	@CsvBindByName(column = "email")
	private String email;
	@CsvBindByName(column = "phone")
	private String phone;
	@CsvBindByName(column = "gender")
	private String gender;
	@CsvBindByName(column = "banned")
	private boolean banned;

}
