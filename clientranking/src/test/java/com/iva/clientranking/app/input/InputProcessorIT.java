package com.iva.clientranking.app.input;

import com.iva.clientranking.app.object.dto.ClientRankingProperites;
import com.iva.clientranking.app.object.dto.InputDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InputProcessorIT {

    @Autowired
    private InputProcessor inputProcessor;
    @Autowired
    private ClientRankingProperites clientRankingProperites;

    @Test
    public void testInputProperties(){
        InputDto result = inputProcessor.process("");
        assertNull(result);
        assertNotNull(clientRankingProperites.getFilterStartDate());
        assertEquals(Integer.valueOf(5), clientRankingProperites.getRankingLimit());
    }

    @Test
    public void testInputParams(){
        InputDto result = inputProcessor.process("c1.csv", "a1.csv", "s1.csv", "p1.csv");
        assertNull(result);
    }

    @Test
    public void testFilesParams(){
        InputDto result = inputProcessor.process("files/test1/c1.csv", "files/test1/a1.csv", "files/test1/s1.csv", "files/test1/p1-test.csv");
        assertNull(result);
    }
    
    @Test
    public void testFilesParsed(){
        InputDto result = inputProcessor.process("files/test1/c1.csv", "files/test1/a1.csv", "files/test1/s1.csv", "files/test1/p1.csv");
        assertNotNull(result);
    }
    
    @Test
    public void testFilesParsedFileEmpty(){
        InputDto result = inputProcessor.process("files/test1/c1.csv", "files/test1/a1.csv", "files/test1/s1.csv", "files/test1/p2.csv");
        assertNull(result);
    }
    
    @Test
    public void testFilesParsingException(){
        InputDto result = inputProcessor.process("files/test1/c1.csv", "files/test1/a1.csv", "files/test1/s1.csv", "files/test1/p3.csv");
        assertNull(result);
    }
}

