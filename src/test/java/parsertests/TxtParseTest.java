package parsertests;

import static org.junit.Assert.*;

import java.util.Set;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.readers.TxtReadList;

public class TxtParseTest {

	private Set<Citizen> readData;

	@Before
	public void clearDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("Citizens");
		db.getCollection("users").deleteMany(new Document());
	}

	@Test
	public void testParse() {
		clearDatabase();
		String resultSt = "[Citizen [firstName=adri, lastName=miron, email=testemail@uniovi.es, "
				+ "birthDate=Sun May 19 00:00:00 CEST 1996, address=C/Asturias, "
				+ "ID=testid, nationality=camboya, NIF=1234, pollingStation=1]]";
		String resultTravis = "[Citizen [firstName=adri, lastName=miron, email=testemail@uniovi.es, "
				+ "birthDate=Sun May 19 00:00:00 UTC 1996, address=C/Asturias, "
				+ "ID=testid, nationality=camboya, NIF=1234, pollingStation=1]]";
		ReadList rl = new TxtReadList();
		readData = rl.parse("src/test/resources/test.txt");
		assertTrue(readData.toString().equals(resultSt) || readData.toString().equals(resultTravis));

	}

}
