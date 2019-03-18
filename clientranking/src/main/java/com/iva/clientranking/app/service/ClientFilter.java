package com.iva.clientranking.app.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.iva.clientranking.app.object.dto.AppointmentDto;
import com.iva.clientranking.app.object.dto.ClientDto;
import com.iva.clientranking.app.object.dto.InputDto;

@Component
public class ClientFilter {

	public List<ClientDto> colectValidClients(InputDto inputDto, List<AppointmentDto> appointmentDtoList) {
		return inputDto.getClientList().stream().filter(c -> !c.isBanned()).map(ClientDto::new)
				.map(c -> collectAppointments(c, appointmentDtoList)).collect(Collectors.toList());
	}

	private ClientDto collectAppointments(ClientDto clientDto, List<AppointmentDto> appointmentDtoList) {
		Long clientPoints = appointmentDtoList.stream().filter(a -> a.getClientId().equals(clientDto.getClientId()))
				.map(a -> a.getTotalAppointmentPoints()).reduce(0L, Long::sum);

		clientDto.setTotalLoyaltyPoints(clientPoints);
		return clientDto;
	}

	public List<ClientDto> selectTopClients(List<ClientDto> clientDtolist, Integer topNumbers) {

		List<ClientDto> sortedClientDtoList = clientDtolist.stream()
				.sorted(Comparator.comparing(ClientDto::getTotalLoyaltyPoints).reversed())
				.collect(Collectors.toList());

		ClientDto clientDto = sortedClientDtoList.get(topNumbers - 1);
		Long topLoyaltyPoints = clientDto.getTotalLoyaltyPoints();

		return sortedClientDtoList.stream()
				.filter(c -> (c.getTotalLoyaltyPoints().compareTo(topLoyaltyPoints) >= 0))
				.collect(Collectors.toList());
	}
	
}
