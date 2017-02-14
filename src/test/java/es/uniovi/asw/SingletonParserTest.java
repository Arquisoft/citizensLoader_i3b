package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.parser.SingletonParser;

public class SingletonParserTest {

	@Test
	public void testGetInstance() {
		assertNotNull(SingletonParser.getInstance());
	}
	
	@Test
	public void testGetExcelParserDefault() {
		assertNotNull(SingletonParser.getInstance().getDefaultExcelReadList());
	}

}
