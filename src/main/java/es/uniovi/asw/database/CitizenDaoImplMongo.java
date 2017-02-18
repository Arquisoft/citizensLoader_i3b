package es.uniovi.asw.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportDefault;

/**
 * DAO implementation for MongoDB database
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public class CitizenDaoImplMongo implements CitizenDao {

	// Extract to properties in the future?

	private final static String host = "localhost";
	private final static int port = 27017;
	private final static String database = "Citizens";
	private final static String collection = "users";

	private MongoClient mongo;
	private DB db;
	private DBCollection users;
	private WriteReport reporter;

	/**
	 * Default constructor that initializes the database from the constants
	 * specified above
	 */
	@SuppressWarnings("deprecation")
	public CitizenDaoImplMongo() {
		this.reporter = new WriteReportDefault();
		this.mongo = new MongoClient(host, port);
		this.db = mongo.getDB(database);
		this.users = db.getCollection(collection);
	}

	/**
	 * This method is used in the test (for using the database for test)
	 * 
	 * 
	 * @param host
	 * @param port
	 * @param database
	 * @param collection
	 */
	@SuppressWarnings("deprecation")
	public CitizenDaoImplMongo(String host, int port, String database,
			String collection) {
		this.reporter = new WriteReportDefault();
		this.mongo = new MongoClient(host, port);
		this.db = mongo.getDB(database);
		this.users = db.getCollection(collection);
	}

	@Override
	public void insert(Citizen c) {
		BasicDBObject document = new BasicDBObject();
		document.put("firstName", c.getName());
		document.put("lastName", c.getlastName());
		document.put("email", c.getEmail());
		document.put("password", c.getPassword());
		document.put("dateOfBirth", c.getbirthDate());
		document.put("address", c.getAddress());
		document.put("nationality", c.getNationality());
		document.put("id", c.getID());
		document.put("nif", c.getNIF());
		document.put("pollingStation", c.getpollingStation());
		try {
			users.insert(document);
		} catch (DuplicateKeyException me) {
			reporter.report(me, "Fallo al insertar en la base de datos: "
					+ "La clave introducida ya está en uso");
		} catch (MongoException me) {
			reporter.report(me, "Fallo al insertar en la base de datos");
		}

	}

	@Override
	public void remove(String ID) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", ID);
		users.remove(document);
	}

	/**
	 * Is there any way to map the objects or something like in JPA without
	 * doing MILLIONS OF CASTINGS????
	 */

	@Override
	public Citizen findById(String ID) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", ID);
		DBCursor cursor = users.find(whereQuery);
		Citizen c = null;
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			c = new Citizen((String) user.get("firstName"), (String) user.get(
					"lastName"), (String) user.get("email"), (Date) user.get(
							"dateOfBirth"), (String) user.get("address"),
					(String) user.get("nationality"), (String) user.get("id"),
					(String) user.get("nif"), (int) user.get("pollingStation"));
		}
		return c;
	}

	@Override
	public List<Citizen> findAll() {

		List<Citizen> allCitizens = new ArrayList<>();

		DBCursor cursor = users.find();
		while (cursor.hasNext()) {
			DBObject user = cursor.next();
			Citizen c = new Citizen((String) user.get("firstName"),
					(String) user.get("lastName"), (String) user.get("email"),
					(Date) user.get("dateOfBirth"), (String) user.get(
							"address"), (String) user.get("nationality"),
					(String) user.get("id"), (String) user.get("nif"),
					(int) user.get("pollingStation"));
			allCitizens.add(c);
		}

		return allCitizens;
	}

	@Override
	public void cleanDatabase() {
		users.remove(new BasicDBObject());

	}

}
