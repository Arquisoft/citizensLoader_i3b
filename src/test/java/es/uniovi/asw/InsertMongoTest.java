package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import es.uniovi.asw.database.InsertDB;
import es.uniovi.asw.database.InsertMongo;
import es.uniovi.asw.parser.Citizen;

public class InsertMongoTest {

	private MongoClient client;
	private DBCollection users;
	private Citizen dummy;
	private Citizen dummy1;
	private Citizen dummy2;
	private InsertDB insert;

	@SuppressWarnings("deprecation")
	@Before
	public void insertCitizen() {
		dummy = new Citizen("a", "b", "a@a.com", "10/10/2010", "a", "a",
				"123456789Z", "132456789", "1234");
		dummy1 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a", "a", "2",
				"132456789", "1234");
		dummy2 = new Citizen("a", "b", "c@a.com", "10/10/2010", "a", "a", "3",
				"132456789", "1234");

		client = new MongoClient("localhost", 27017);
		DB db = client.getDB("test");
		db.getCollection("test").remove(new BasicDBObject());
		users = db.getCollection("test");
		users.createIndex(new BasicDBObject("id", 1),
				new BasicDBObject("unique", true));

		insert = new InsertMongo(users);
	}

	@Test
	public void testInsert() {
		insert.insert(dummy);
		DBCursor cursor = users.find();

		assertEquals(cursor.size(), 1);

		if (cursor.hasNext()) {
			assertEquals(dummy.getID(), cursor.next().get("id"));
		}

	}

	@Test
	public void testMultipleInsert() {
		insert.insert(dummy);
		insert.insert(dummy1);
		insert.insert(dummy2);

		DBCursor cursor = users.find();

		assertEquals(cursor.size(), 3);

		if (cursor.hasNext()) {
			assertEquals(dummy.getID(), cursor.next().get("id"));
		}
		if (cursor.hasNext()) {
			assertEquals(dummy1.getID(), cursor.next().get("id"));
		}
		if (cursor.hasNext()) {
			assertEquals(dummy2.getID(), cursor.next().get("id"));
		}

	}

	@Test
	public void testNoDuplicates() {
		insert.insert(dummy);
		insert.insert(dummy);

		DBCursor cursor = users.find();
		assertEquals(cursor.size(), 1);
	}

	@After
	public void closeClient() {
		client.close();
	}

}
