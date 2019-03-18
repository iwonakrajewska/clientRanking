package com.iva.clientranking.app.object.dto;

import com.iva.clientranking.app.object.entity.Appointment;

import lombok.Data;

@Data
public class AppointmentDto {

	private String appointmentId;
	private String clientId;
	private Long servicePoints;
	private Long purchasePoints;
	private Long totalAppointmentPoints;

	public AppointmentDto(Appointment appointment) {
		this.appointmentId = appointment.getId();
		this.clientId = appointment.getClientId();
	}

}
