package com.iva.clientranking.app.output;

import java.util.List;

import org.springframework.stereotype.Component;

import com.iva.clientranking.app.object.dto.ClientDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OutputParser {

	public void formatClientlist(List<ClientDto> topCustomers) {
		for (int i =0 ; i < topCustomers.size(); i++) {
			ClientDto clientDto = topCustomers.get(i);
			log.info("Ranking: \t{}\t{}\t{}\t{}", i+1, clientDto.getTotalLoyaltyPoints(), clientDto.getFirstName(), clientDto.getEmail());
		}
	}
	
}
