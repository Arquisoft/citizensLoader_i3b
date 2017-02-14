package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.readers.ExcelReadList;

public class ExcelParseTest {

	private Set<Citizen> readData;

	@Test
	public void testParse() {
		String result = "[Citizen [firstName=Juan, lastName=Torres"
				+ " Pardo, email=juan@example.com, birthDate=Thu Oct"
				+ " 10 00:00:00 CET 1985, address=C/ Federico García Lorca 2," + " ID=90500084Y, "
				+ "nationality=Español, NIF=1.0, pollingStation=1]]";
		String resultForTravis = "[Citizen [firstName=Juan, lastName=Torres"
				+ " Pardo, email=juan@example.com, birthDate=Thu Oct"
				+ " 10 00:00:00 UTC 1985, address=C/ Federico García Lorca 2," + " ID=90500084Y, "
				+ "nationality=Español, NIF=1.0, pollingStation=1]]";

		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test2.xlsx");

		assertTrue(readData.toString().equals(result) || readData.toString().equals(resultForTravis));
	}

	@Test
	public void fileNotFound() {
		ReadList rl = new ExcelReadList();
		readData = rl.parse("archivoQueNoExiste");

	}

	@Test
	public void testFilaSinDNI() {
		ReadList rl = new ExcelReadList();
		readData = rl.parse("src/test/resources/test3.xlsx");
	}

}
