package com.iva.clientranking.app.object.dto;

import java.nio.file.Path;
import java.util.List;

import com.iva.clientranking.app.object.entity.Appointment;
import com.iva.clientranking.app.object.entity.Client;
import com.iva.clientranking.app.object.entity.Purchase;
import com.iva.clientranking.app.object.entity.Service;

import lombok.Data;

@Data
public class InputDto {

	private Path clientFilePath;
	private Path appointmentFilePath;
	private Path serviceFilePath;
	private Path purchaseFilePath;

	private List<Client> clientList;
	private List<Appointment> appointmentList;
	private List<Service> serviceList;
	private List<Purchase> purchaseList;

}
