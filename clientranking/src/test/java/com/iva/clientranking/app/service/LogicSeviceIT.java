package com.iva.clientranking.app.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iva.clientranking.app.input.InputProcessor;
import com.iva.clientranking.app.object.dto.AppointmentDto;
import com.iva.clientranking.app.object.dto.ClientDto;
import com.iva.clientranking.app.object.dto.InputDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogicSeviceIT {

	@Autowired
	private InputProcessor inputProcessor;
	@Autowired
	private LogicSevice logicService;

	@Test
	public void testRunRanking() {

		InputDto inputDto = inputProcessor.process("files/test1/7c.csv", "files/test1/7a.csv", "files/test1/7s.csv",
				"files/test1/7p.csv");
		List<ClientDto> topClientDtolist = logicService.runRanking(inputDto);

		assertEquals(7, topClientDtolist.size());
		assertEquals(Long.valueOf(110), topClientDtolist.get(0).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(90), topClientDtolist.get(1).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(50), topClientDtolist.get(2).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(40), topClientDtolist.get(3).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(30), topClientDtolist.get(4).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(30), topClientDtolist.get(5).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(30), topClientDtolist.get(6).getTotalLoyaltyPoints());
	}
	
}
