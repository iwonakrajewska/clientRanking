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
import com.iva.clientranking.app.object.dto.InputDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppointmentFilterIT {

	@Autowired
	private InputProcessor inputProcessor;
	@Autowired
	private AppointmentFilter appointmentFilter;

	@Test
	public void testClientFilter() {

		InputDto inputDto = inputProcessor.process("files/test1/5c.csv", "files/test1/5a.csv", "files/test1/5s.csv",
				"files/test1/5p.csv");
		List<AppointmentDto> appointmentDtoList = appointmentFilter.collectValidAppointemntsPoints(inputDto);
		assertEquals(3, appointmentDtoList.size());
		assertEquals("bd109d64-b0cd-49bf-a4b4-fa8276135c24", appointmentDtoList.get(0).getAppointmentId());
		assertEquals("6907ef58-5db5-4cd0-b740-ea166093bcab", appointmentDtoList.get(0).getClientId());
		assertEquals(Long.valueOf(60), appointmentDtoList.get(0).getServicePoints());
		assertEquals(Long.valueOf(0), appointmentDtoList.get(0).getPurchasePoints());
		assertEquals(Long.valueOf(60), appointmentDtoList.get(0).getTotalAppointmentPoints());
		
		assertEquals("a670567d-bb09-4eaa-8e15-9ffdb1b50e7b", appointmentDtoList.get(1).getAppointmentId());
		assertEquals("379eac1e-1c79-4478-b83e-17a726d154a7", appointmentDtoList.get(1).getClientId());
		assertEquals(Long.valueOf(0), appointmentDtoList.get(1).getServicePoints());
		assertEquals(Long.valueOf(10), appointmentDtoList.get(1).getPurchasePoints());
		assertEquals(Long.valueOf(10), appointmentDtoList.get(1).getTotalAppointmentPoints());
		
		assertEquals("8a6dd2d9-cd8d-4b46-9971-cc7d4fcd0bff", appointmentDtoList.get(2).getAppointmentId());
		assertEquals("690133c1-785c-49b1-8c59-4416a8545260", appointmentDtoList.get(2).getClientId());
		assertEquals(Long.valueOf(95), appointmentDtoList.get(2).getServicePoints());
		assertEquals(Long.valueOf(25), appointmentDtoList.get(2).getPurchasePoints());
		assertEquals(Long.valueOf(120), appointmentDtoList.get(2).getTotalAppointmentPoints());

	}
}
