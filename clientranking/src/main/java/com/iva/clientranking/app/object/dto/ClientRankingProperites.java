package com.iva.clientranking.app.object.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ClientRankingProperites {

	@Value("${clientRanking.ranking.limit:50}")
	private Integer rankingLimit;

	@Value("#{T(java.time.LocalDate).parse('${clientRanking.ranking.startDate:2018-01-01}')}")
	private LocalDate filterStartDate;

	public ZonedDateTime getZonedFilterDateTime() {
		return filterStartDate.atStartOfDay(ZoneId.systemDefault());
	}
}
