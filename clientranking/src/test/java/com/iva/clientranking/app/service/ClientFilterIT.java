package com.iva.clientranking.app.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iva.clientranking.app.input.InputProcessor;
import com.iva.clientranking.app.object.dto.AppointmentDto;
import com.iva.clientranking.app.object.dto.ClientDto;
import com.iva.clientranking.app.object.dto.ClientRankingProperites;
import com.iva.clientranking.app.object.dto.InputDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientFilterIT {

	@Autowired
	private InputProcessor inputProcessor;
	@Autowired
	private ClientFilter clientFilter;
	@Autowired
	private AppointmentFilter appointmentFilter;
	@Autowired
	private ClientRankingProperites clientRankingProperites;

	@Test
	public void testClientFilterBanned() {
		InputDto inputDto = inputProcessor.process("files/test1/4c.csv", "files/test1/5a.csv", "files/test1/5s.csv",
				"files/test1/5p.csv");
		List<ClientDto> clientDtoList = clientFilter.colectValidClients(inputDto, new ArrayList<AppointmentDto>());
		assertEquals(4, clientDtoList.size());
		assertEquals("Dori", clientDtoList.get(0).getFirstName());
		assertEquals("Gordon", clientDtoList.get(1).getFirstName());
		assertEquals("Malia", clientDtoList.get(2).getFirstName());
		assertEquals("Diego", clientDtoList.get(3).getFirstName());
	}

	@Test
	public void testClientFilter() {
		InputDto inputDto = inputProcessor.process("files/test1/6c.csv", "files/test1/6a.csv", "files/test1/6s.csv",
				"files/test1/6p.csv");

		List<AppointmentDto> appointmentDtoList = appointmentFilter.collectValidAppointemntsPoints(inputDto);
		List<ClientDto> clientDtoList = clientFilter.colectValidClients(inputDto, appointmentDtoList);

		assertEquals(4, clientDtoList.size());
		assertEquals("Dori", clientDtoList.get(0).getFirstName());
		assertEquals(Long.valueOf(52), clientDtoList.get(0).getTotalLoyaltyPoints());
		assertEquals("Gordon", clientDtoList.get(1).getFirstName());
		assertEquals(Long.valueOf(0), clientDtoList.get(1).getTotalLoyaltyPoints());
		assertEquals("Malia", clientDtoList.get(2).getFirstName());
		assertEquals(Long.valueOf(0), clientDtoList.get(2).getTotalLoyaltyPoints());
		assertEquals("Diego", clientDtoList.get(3).getFirstName());
		assertEquals(Long.valueOf(180), clientDtoList.get(3).getTotalLoyaltyPoints());
	}

	@Test
	public void testTopNumbers() {
		List<ClientDto> clientDtoList = new ArrayList<>();
		ClientDto c1 = new ClientDto();
		c1.setFirstName("n1");
		c1.setTotalLoyaltyPoints(Long.valueOf(10));
		clientDtoList.add(c1);
		ClientDto c2 = new ClientDto();
		c2.setFirstName("n2");
		c2.setTotalLoyaltyPoints(Long.valueOf(20));
		clientDtoList.add(c2);
		ClientDto c3 = new ClientDto();
		c3.setFirstName("n3");
		c3.setTotalLoyaltyPoints(Long.valueOf(30));
		clientDtoList.add(c3);
		ClientDto c4 = new ClientDto();
		c4.setFirstName("n4");
		c4.setTotalLoyaltyPoints(Long.valueOf(5));
		clientDtoList.add(c4);
		ClientDto c5 = new ClientDto();
		c5.setFirstName("n5");
		c5.setTotalLoyaltyPoints(Long.valueOf(25));
		clientDtoList.add(c5);
		ClientDto c6 = new ClientDto();
		c6.setFirstName("n6");
		c6.setTotalLoyaltyPoints(Long.valueOf(35));
		clientDtoList.add(c6);

		List<ClientDto> topClientDtoList = clientFilter.selectTopClients(clientDtoList,
				clientRankingProperites.getRankingLimit());
		assertEquals(5, topClientDtoList.size());
		assertEquals(Long.valueOf(35), topClientDtoList.get(0).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(30), topClientDtoList.get(1).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(25), topClientDtoList.get(2).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(20), topClientDtoList.get(3).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(10), topClientDtoList.get(4).getTotalLoyaltyPoints());
	}
	
	@Test
	public void testTopMultipleNumbers() {
		List<ClientDto> clientDtoList = new ArrayList<>();
		ClientDto c1 = new ClientDto();
		c1.setFirstName("n1");
		c1.setTotalLoyaltyPoints(Long.valueOf(10));
		clientDtoList.add(c1);
		ClientDto c2 = new ClientDto();
		c2.setFirstName("n2");
		c2.setTotalLoyaltyPoints(Long.valueOf(20));
		clientDtoList.add(c2);
		ClientDto c3 = new ClientDto();
		c3.setFirstName("n3");
		c3.setTotalLoyaltyPoints(Long.valueOf(30));
		clientDtoList.add(c3);
		ClientDto c4 = new ClientDto();
		c4.setFirstName("n4");
		c4.setTotalLoyaltyPoints(Long.valueOf(5));
		clientDtoList.add(c4);
		ClientDto c5 = new ClientDto();
		c5.setFirstName("n5");
		c5.setTotalLoyaltyPoints(Long.valueOf(25));
		clientDtoList.add(c5);
		ClientDto c6 = new ClientDto();
		c6.setFirstName("n6");
		c6.setTotalLoyaltyPoints(Long.valueOf(35));
		clientDtoList.add(c6);
		ClientDto c7 = new ClientDto();
		c7.setFirstName("n7");
		c7.setTotalLoyaltyPoints(Long.valueOf(30));
		clientDtoList.add(c7);
		ClientDto c8 = new ClientDto();
		c8.setFirstName("n8");
		c8.setTotalLoyaltyPoints(Long.valueOf(20));
		clientDtoList.add(c8);
		ClientDto c9 = new ClientDto();
		c9.setFirstName("n9");
		c9.setTotalLoyaltyPoints(Long.valueOf(10));
		clientDtoList.add(c9);
		ClientDto c10 = new ClientDto();
		c10.setFirstName("n10");
		c10.setTotalLoyaltyPoints(Long.valueOf(20));
		clientDtoList.add(c10);
		
		List<ClientDto> topClientDtoList = clientFilter.selectTopClients(clientDtoList,
				clientRankingProperites.getRankingLimit());
		assertEquals(7, topClientDtoList.size());
		assertEquals(Long.valueOf(35), topClientDtoList.get(0).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(30), topClientDtoList.get(1).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(30), topClientDtoList.get(2).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(25), topClientDtoList.get(3).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(20), topClientDtoList.get(4).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(20), topClientDtoList.get(4).getTotalLoyaltyPoints());
		assertEquals(Long.valueOf(20), topClientDtoList.get(4).getTotalLoyaltyPoints());
	}
}
