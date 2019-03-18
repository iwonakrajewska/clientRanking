package com.iva.clientranking.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainRunnerIT {

	public static String[] sampleFiles = new String[] { "files/test1/7c.csv", "files/test1/7a.csv",
			"files/test1/7s.csv", "files/test1/7p.csv" };

	@Autowired
	private MainRunner mainRunner;


	@Test
	public void testRunRanking() {
		mainRunner.run(sampleFiles);
	}

}
