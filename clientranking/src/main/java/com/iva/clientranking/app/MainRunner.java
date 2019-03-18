package com.iva.clientranking.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iva.clientranking.app.input.InputProcessor;
import com.iva.clientranking.app.object.dto.ClientDto;
import com.iva.clientranking.app.object.dto.InputDto;
import com.iva.clientranking.app.output.OutputParser;
import com.iva.clientranking.app.service.LogicSevice;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MainRunner implements CommandLineRunner {

	private InputProcessor inputProcessor;
	private LogicSevice logicService;
	private OutputParser outputParser;
	

	@Autowired
	public MainRunner(InputProcessor inputProcessor, LogicSevice logicService, OutputParser outputParser) {
		super();
		this.inputProcessor = inputProcessor;
		this.logicService = logicService;
		this.outputParser = outputParser;
	}



	@Override
	public void run(String... args) {
		log.info("EXECUTING : command line runner");

		InputDto inputDto = inputProcessor.process(args);
		if (inputDto != null) {
			List<ClientDto> topCustomers = logicService.runRanking(inputDto);
			outputParser.formatClientlist(topCustomers);
		}
	}

}
