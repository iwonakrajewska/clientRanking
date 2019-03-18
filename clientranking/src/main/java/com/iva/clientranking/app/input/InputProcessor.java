package com.iva.clientranking.app.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iva.clientranking.app.input.csv.CsvParser;
import com.iva.clientranking.app.object.dto.InputDto;
import com.iva.clientranking.app.object.exception.ClientRankingException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InputProcessor {

	private InputValidator inputValidator;
	private CsvParser csvParser;

	@Autowired
	public InputProcessor(InputValidator inputValidator, CsvParser csvParser) {
		super();
		this.inputValidator = inputValidator;
		this.csvParser = csvParser;
	}

	public InputDto process(String... args) {
		InputDto inputDto = inputValidator.verifyFilesPresent(args);
		if (inputDto == null) {
			printUsage();
			return null;
		}
		try {
			inputValidator.validateProperties();
			csvParser.parseInputParameters(inputDto);
		} catch (ClientRankingException e) {
			log.error("Problem processing input: {}", e.getMessage());
			return null;
		}
		if (inputValidator.validateFilesRecordsPresent(inputDto)) {
			return inputDto;
		}
		return null;
	}

	private void printUsage() {
		log.info("Input parameters: client.csv, appointment.csv, service.csv, purchase.csv");
	}

}
