package com.iva.clientranking.app.object.dto;

import com.iva.clientranking.app.object.entity.Client;

import lombok.Data;

@Data
public class ClientDto {

	private String clientId;
	private String firstName;
	private String lastName;
	private String email;
	private Long totalLoyaltyPoints;

	public ClientDto() {
	}

	public ClientDto(Client client) {
		this.clientId = client.getId();
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.email = client.getEmail();
	}

}
