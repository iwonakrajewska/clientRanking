package com.iva.clientranking.app.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iva.clientranking.app.object.dto.ClientRankingProperites;
import com.iva.clientranking.app.object.dto.InputDto;
import com.iva.clientranking.app.object.entity.Appointment;
import com.iva.clientranking.app.object.entity.Client;
import com.iva.clientranking.app.object.entity.Purchase;
import com.iva.clientranking.app.object.entity.Service;
import com.iva.clientranking.app.object.exception.ClientRankingException;

public class InputValidatorTest {

	@InjectMocks
	private InputValidator inputValidator;

	@Mock
	private ClientRankingProperites clientRankingProperites;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(clientRankingProperites.getFilterStartDate()).thenReturn(LocalDate.now());
		when(clientRankingProperites.getRankingLimit()).thenReturn(1);
	}

	@Test(expected = ClientRankingException.class)
	public void checkLimitEmpty() throws ClientRankingException {
		when(clientRankingProperites.getRankingLimit()).thenReturn(null);
		inputValidator.validateProperties();
	}

	@Test(expected = ClientRankingException.class)
	public void checkLimitNegative() throws ClientRankingException {
		when(clientRankingProperites.getRankingLimit()).thenReturn(-1);
		inputValidator.validateProperties();
	}

	@Test
	public void checkLimitPositive() throws ClientRankingException {
		when(clientRankingProperites.getRankingLimit()).thenReturn(5);
		inputValidator.validateProperties();
	}

	@Test(expected = ClientRankingException.class)
	public void checkLimitZero() throws ClientRankingException {
		when(clientRankingProperites.getRankingLimit()).thenReturn(0);
		inputValidator.validateProperties();
	}

	@Test(expected = ClientRankingException.class)
	public void checkEmptyDate() throws ClientRankingException {
		when(clientRankingProperites.getFilterStartDate()).thenReturn(null);
		inputValidator.validateProperties();
	}
	

	@Test
	public void validateFilesRecordsEmpty() {
		InputDto inputDto = new InputDto();
		assertFalse(inputValidator.validateFilesRecordsPresent(inputDto));
	}
	

	@Test
	public void validateFilesRecordsPresent() {
		InputDto inputDto = new InputDto();
		inputDto.setClientList(new ArrayList<Client>());
		inputDto.getClientList().add(new Client());
		inputDto.setPurchaseList(new ArrayList<Purchase>());
		inputDto.getPurchaseList().add(new Purchase());
		inputDto.setAppointmentList(new ArrayList<Appointment>());
		inputDto.getAppointmentList().add(new Appointment());
		inputDto.setServiceList(new ArrayList<Service>());
		inputDto.getServiceList().add(new Service());
		assertTrue(inputValidator.validateFilesRecordsPresent(inputDto));
	}
	
}
