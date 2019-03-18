package com.iva.clientranking.app.input;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iva.clientranking.app.object.dto.ClientRankingProperites;
import com.iva.clientranking.app.object.dto.InputDto;
import com.iva.clientranking.app.object.exception.ClientRankingException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InputValidator {

	private ClientRankingProperites clientRankingProperites;

	@Autowired
	public InputValidator(ClientRankingProperites clientRankingProperites) {
		super();
		this.clientRankingProperites = clientRankingProperites;
	}

	public void validateProperties() throws ClientRankingException {
		if (clientRankingProperites.getRankingLimit() == null
				|| clientRankingProperites.getRankingLimit().compareTo(0) <= 0) {
			throw new ClientRankingException("RanlingLimit must be higher than 0");
		}
		if (clientRankingProperites.getFilterStartDate() == null) {
			throw new ClientRankingException("RanlingStartDate must be a valid Date");
		}
	}

	public InputDto verifyFilesPresent(String... args) {
		if (args == null || args.length != 4) {
			return null;
		}
		InputDto inputDto = new InputDto();

		Path clientFilePath = Paths.get(args[0]);
		if (clientFilePath.toFile().exists()) {
			inputDto.setClientFilePath(clientFilePath);
		} else {
			log.error("Client file not present: {}", args[0]);
		}
		Path appointmentFilePath = Paths.get(args[1]);
		if (appointmentFilePath.toFile().exists()) {
			inputDto.setAppointmentFilePath(appointmentFilePath);
		} else {
			log.error("Appointment file not present: {}", args[1]);
		}
		Path serviceFilePath = Paths.get(args[2]);
		if (serviceFilePath.toFile().exists()) {
			inputDto.setServiceFilePath(serviceFilePath);
		} else {
			log.error("Service file not present: {}", args[2]);
		}
		Path purchaseFilePath = Paths.get(args[3]);
		if (purchaseFilePath.toFile().exists()) {
			inputDto.setPurchaseFilePath(purchaseFilePath);
		} else {
			log.error("Purchase file not present: {}", args[3]);
		}
		if (inputDto.getClientFilePath() == null || inputDto.getAppointmentFilePath() == null
				|| inputDto.getServiceFilePath() == null || inputDto.getPurchaseFilePath() == null) {
			return null;
		}
		return inputDto;
	}

	public boolean validateFilesRecordsPresent(InputDto inputDto) {
		if (CollectionUtils.isEmpty(inputDto.getClientList())) {
			log.error("0 client records, check parser");
			return false;
		}
		if (CollectionUtils.isEmpty(inputDto.getAppointmentList())) {
			log.error("0 appointment records, check parser");
			return false;
		}
		if (CollectionUtils.isEmpty(inputDto.getServiceList())) {
			log.error("0 service records, check parser");
			return false;
		}
		if (CollectionUtils.isEmpty(inputDto.getPurchaseList())) {
			log.error("0 purchase records, check parser");
			return false;
		}
		return true;
	}
}
