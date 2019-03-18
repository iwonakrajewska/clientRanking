package com.iva.clientranking.app.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iva.clientranking.app.object.dto.AppointmentDto;
import com.iva.clientranking.app.object.dto.ClientRankingProperites;
import com.iva.clientranking.app.object.dto.InputDto;

@Component
public class AppointmentFilter {

	private ClientRankingProperites clientRankingProperites;

	@Autowired
	public AppointmentFilter(ClientRankingProperites clientRankingProperites) {
		super();
		this.clientRankingProperites = clientRankingProperites;
	}

	public List<AppointmentDto> collectValidAppointemntsPoints(final InputDto inputDto) {
		ZonedDateTime fiterStart = clientRankingProperites.getZonedFilterDateTime();
		return  inputDto.getAppointmentList()
				.stream()
				.filter(a -> (a.getStartTime().isAfter(fiterStart) || a.getStartTime().isEqual(fiterStart)))
				.map(AppointmentDto::new)
				.map(a -> sumServicePoints(inputDto, a))
				.map(a -> sumPurchasePoints(inputDto, a))
				.map(a -> totalPoints(a))
				.collect(Collectors.toList());
	}

	private AppointmentDto sumServicePoints(InputDto inputDto, AppointmentDto a) {
		Long sumPoints = inputDto.getServiceList().stream()
				.filter(s -> s.getAppointmentId().equals(a.getAppointmentId()))
				.map(s -> s.getLoyaltyPoints())
				.reduce(0L, Long::sum);
		a.setServicePoints(sumPoints);
		return a;
	}

	private AppointmentDto sumPurchasePoints(InputDto inputDto, AppointmentDto a) {
		Long sumPoints = inputDto.getPurchaseList().stream()
				.filter(s -> s.getAppointmentId().equals(a.getAppointmentId()))
				.map(p -> p.getLoyaltyPoints())
				.reduce(0L, Long::sum);
		a.setPurchasePoints(sumPoints);
		return a;
	}
	
	private AppointmentDto totalPoints(AppointmentDto a) {	
		a.setTotalAppointmentPoints(Long.sum(a.getServicePoints(), a.getPurchasePoints()));
		return a;
	}

}
