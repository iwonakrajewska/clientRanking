package com.iva.clientranking.app.output;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iva.clientranking.app.object.dto.ClientDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OutputParserTest {

	@Autowired
	private OutputParser outputParser;

	@Test
	public void seePrint() {
		List<ClientDto> topCustomers = new ArrayList<>();
		ClientDto c1 = new ClientDto();
		c1.setTotalLoyaltyPoints(100L);
		c1.setFirstName("Name1");
		c1.setEmail("email1@aa.aa");
		topCustomers.add(c1);
		ClientDto c2 = new ClientDto();
		c2.setTotalLoyaltyPoints(80L);
		c2.setFirstName("Name2");
		c2.setEmail("email2@bb.bb");
		topCustomers.add(c2);
		outputParser.formatClientlist(topCustomers);
	}
}
