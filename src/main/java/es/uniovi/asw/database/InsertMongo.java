package es.uniovi.asw.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import es.uniovi.asw.parser.Citizen;

public class InsertMongo implements InsertDB {

	private DB db;

	public InsertMongo(DB db) {
		this.db = db;
	}

	@Override
	public void insert(Citizen c) {

		DBCollection table = db.getCollection("user");
		BasicDBObject document = new BasicDBObject();
		document.put("name", c.getName());
		document.put("surname", c.getlastName());
		document.put("email", c.getEmail());
		document.put("dateOfBirth", c.getID());
		document.put("address", c.getAddress());
		document.put("nationality", c.getNationality());
		document.put("dni", c.getID());
		document.put("nif", c.getNIF());
		document.put("pollingStation", c.getpollingStation());
		table.insert(document);
	}

}
