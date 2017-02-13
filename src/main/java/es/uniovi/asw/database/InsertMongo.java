package es.uniovi.asw.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import es.uniovi.asw.parser.Citizen;

public class InsertMongo implements InsertDB {

	private DBCollection users;

	public InsertMongo(DBCollection users) {
		this.users = users;
	}

	@Override
	public void insert(Citizen c) {

		BasicDBObject document = new BasicDBObject();
		document.put("firstName", c.getName());
		document.put("lastName", c.getlastName());
		document.put("email", c.getEmail());
		document.put("dateOfBirth", c.getID());
		document.put("address", c.getAddress());
		document.put("nationality", c.getNationality());
		document.put("id", c.getID());
		document.put("nif", c.getNIF());
		document.put("pollingStation", c.getpollingStation());
		users.insert(document);
	}

}
