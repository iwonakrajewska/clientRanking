package com.iva.clientranking.app.input.csv;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.iva.clientranking.app.input.csv.CsvParser;
import com.iva.clientranking.app.object.dto.InputDto;
import com.iva.clientranking.app.object.entity.Appointment;
import com.iva.clientranking.app.object.entity.Client;
import com.iva.clientranking.app.object.entity.Purchase;
import com.iva.clientranking.app.object.entity.Service;
import com.iva.clientranking.app.object.exception.ClientRankingException;

public class CsvParserTest {

	@InjectMocks
	private CsvParser csvParser;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testParsingClientFile() throws ClientRankingException {
		InputDto inputDto = new InputDto();
		inputDto.setClientFilePath(Paths.get("files/test1/c1.csv"));
		csvParser.parseInputParameters(inputDto);
		assertNotNull(inputDto.getClientList());
	}

	@Test(expected = NoSuchFileException.class)
	public void testParserNoFile() throws Exception {
		csvParser.beanBuilderExample(Paths.get("files/test1/c1-test.csv"), Client.class);
		fail("Should be here");
	}

	@Test
	public void testParserNull() throws Exception {
		List<Client> result = csvParser.beanBuilderExample(null, Client.class);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testParser100() throws Exception {
		List<Client> result = csvParser.beanBuilderExample(Paths.get("files/test1/c1.csv"), Client.class);
		assertEquals(100, result.size());
		Client client1 = result.get(0);
		assertEquals("e0b8ebfc-6e57-4661-9546-328c644a3764", client1.getId());
		assertEquals("Dori", client1.getFirstName());
		assertEquals("Dietrich", client1.getLastName());
		assertEquals("patrica@keeling.net", client1.getEmail());
		assertEquals("(272) 301-6356", client1.getPhone());
		assertEquals("Male", client1.getGender());
		assertFalse(client1.isBanned());
		Client client100 = result.get(99);
		assertEquals("1163f823-3849-4294-8a7c-66a556f5122c", client100.getId());
		assertEquals("Hugo", client100.getFirstName());
		assertEquals("MacGyver", client100.getLastName());
		assertEquals("jason@wehnermarks.co", client100.getEmail());
		assertEquals("(875) 159-3581", client100.getPhone());
		assertEquals("Male", client100.getGender());
		assertFalse(client100.isBanned());
	}

	@Test(expected = RuntimeException.class)
	public void testParserC3() throws Exception {
		csvParser.beanBuilderExample(Paths.get("files/test1/c3.csv"), Client.class);
		fail("Should be here");
	}

	@Test
	public void testParsingAppointmentFile() throws ClientRankingException {
		InputDto inputDto = new InputDto();
		inputDto.setAppointmentFilePath(Paths.get("files/test1/a1.csv"));
		csvParser.parseInputParameters(inputDto);
		assertNotNull(inputDto.getAppointmentList());
		assertEquals(490, inputDto.getAppointmentList().size());
		Appointment appointment1 = inputDto.getAppointmentList().get(0);
		assertEquals("7416ebc3-12ce-4000-87fb-82973722ebf4", appointment1.getId());
		assertEquals("263f67fa-ce8f-447b-98cf-317656542216", appointment1.getClientId());
		assertNotNull(appointment1.getStartTime());
		assertNotNull(appointment1.getEndTime());
	}

	@Test
	public void testParsingServiceFile() throws ClientRankingException {
		InputDto inputDto = new InputDto();
		inputDto.setServiceFilePath(Paths.get("files/test1/s1.csv"));
		csvParser.parseInputParameters(inputDto);
		assertNotNull(inputDto.getServiceList());
		assertEquals(1031, inputDto.getServiceList().size());
		Service service1 = inputDto.getServiceList().get(0);
		assertEquals("f1fc7009-0c44-4f89-ac98-5de9ce58095c", service1.getId());
		assertEquals("7416ebc3-12ce-4000-87fb-82973722ebf4", service1.getAppointmentId());
		assertEquals("Full Head Colour", service1.getName());
		assertEquals(Long.valueOf(80), service1.getLoyaltyPoints());
		assertEquals(BigDecimal.valueOf(85.0), service1.getPrice());
	}

	@Test
	public void testParsingPurchaseFile() throws ClientRankingException {
		InputDto inputDto = new InputDto();
		inputDto.setPurchaseFilePath(Paths.get("files/test1/p1.csv"));
		csvParser.parseInputParameters(inputDto);
		assertNotNull(inputDto.getPurchaseList());
		assertEquals(476, inputDto.getPurchaseList().size());
		Purchase purchase1 = inputDto.getPurchaseList().get(0);
		assertEquals("d2d3b92d-f9b5-48c5-bf31-88c28e3b73ac", purchase1.getId());
		assertEquals("7416ebc3-12ce-4000-87fb-82973722ebf4", purchase1.getAppointmentId());
		assertEquals("Shampoo", purchase1.getName());
		assertEquals(Long.valueOf(20), purchase1.getLoyaltyPoints());
		assertEquals(BigDecimal.valueOf(19.5), purchase1.getPrice());	
	}
}
