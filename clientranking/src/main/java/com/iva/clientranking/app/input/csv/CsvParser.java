package com.iva.clientranking.app.input.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.iva.clientranking.app.object.dto.InputDto;
import com.iva.clientranking.app.object.entity.Appointment;
import com.iva.clientranking.app.object.entity.Client;
import com.iva.clientranking.app.object.entity.Purchase;
import com.iva.clientranking.app.object.entity.Service;
import com.iva.clientranking.app.object.exception.ClientRankingException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CsvParser {

	public void parseInputParameters(InputDto inputDto) throws ClientRankingException {
		try {
			log.info("Parsing client file");
			inputDto.setClientList(beanBuilderExample(inputDto.getClientFilePath(), Client.class));
			log.info("Parsing appointment file");
			inputDto.setAppointmentList(beanBuilderExample(inputDto.getAppointmentFilePath(), Appointment.class));
			log.info("Parsing service file");
			inputDto.setServiceList(beanBuilderExample(inputDto.getServiceFilePath(), Service.class));
			log.info("Parsing purchase file");
			inputDto.setPurchaseList(beanBuilderExample(inputDto.getPurchaseFilePath(), Purchase.class));
			log.info("4 files parsed");
		} catch (Exception e) {
			log.error("Problem parsing files: {}".concat(e.getMessage()));
			throw new ClientRankingException("Problem parsing files");
		}
	}

	<T> List<T> beanBuilderExample(Path path, Class<T> clazz) throws IOException {
		if (path == null) {
			return Collections.emptyList();
		}
		MappingStrategy<T> ms = new HeaderColumnNameMappingStrategy<>();
		ms.setType(clazz);

		try (Reader reader2 = Files.newBufferedReader(path)) {
			CsvToBean<T> cb2 = new CsvToBeanBuilder<T>(reader2).withType(clazz).withMappingStrategy(ms).build();
			return cb2.parse();
		}
	}

}
