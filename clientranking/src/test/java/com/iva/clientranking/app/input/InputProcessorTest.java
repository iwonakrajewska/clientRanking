package com.iva.clientranking.app.input;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iva.clientranking.app.object.dto.InputDto;
import org.springframework.beans.factory.annotation.Autowired;

public class InputProcessorTest {

	@InjectMocks
	private InputProcessor inputProcessor;
	@Mock
	private InputValidator inputValidator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void triggerEmptyParameters() {
		InputDto result = inputProcessor.process(null);
		assertNull(result);
	}

	@Test
	public void trigger3Parameters() {
		InputDto result = inputProcessor.process("", "", "");
		assertNull(result);
	}
	@Test
	public void trigger5Parameters() {
		InputDto result = inputProcessor.process("", "", "", "", "");
		assertNull(result);
	}
}
