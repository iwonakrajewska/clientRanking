package com.iva.clientranking.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iva.clientranking.app.object.dto.AppointmentDto;
import com.iva.clientranking.app.object.dto.ClientDto;
import com.iva.clientranking.app.object.dto.ClientRankingProperites;
import com.iva.clientranking.app.object.dto.InputDto;

@Service
public class LogicSevice {

	private AppointmentFilter appointmentFilter;
	private ClientFilter clientFilter;
	private ClientRankingProperites clientRankingProperites;

	@Autowired
	public LogicSevice(AppointmentFilter appointmentFilter, ClientFilter clientFilter,
			ClientRankingProperites clientRankingProperites) {
		super();
		this.appointmentFilter = appointmentFilter;
		this.clientFilter = clientFilter;
		this.clientRankingProperites = clientRankingProperites;
	}

	public List<ClientDto> runRanking(InputDto inputDto) {
		List<AppointmentDto> appointmentDtoList = appointmentFilter.collectValidAppointemntsPoints(inputDto);
		List<ClientDto> clientDtolist = clientFilter.colectValidClients(inputDto, appointmentDtoList);
		return clientFilter.selectTopClients(clientDtolist,
				clientRankingProperites.getRankingLimit());
	}

}
