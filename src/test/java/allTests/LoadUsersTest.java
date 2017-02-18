package allTests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.bson.Document;
import org.junit.Test;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import es.uniovi.asw.LoadUsers;

public class LoadUsersTest {

	@Test
	public void testRunInsert() {
		// Clears the database before the test.
		clearDatabase();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// Tests how the data is inserted correctly into the database for the
		// first time.
		System.setOut(new PrintStream(outContent));
		LoadUsers.main("src/test/resources/test2.xlsx");
		assertTrue(outContent.toString().contains("90500084Y letter sent."));

	}

	public void clearDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase db = mongoClient.getDatabase("Citizens");
		db.getCollection("users").deleteMany(new Document());
	}

}
