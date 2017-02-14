package es.uniovi.asw.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportPort;

public class InsertMongo implements InsertDB {

	private DBCollection users;
	private MongoClient mongo;
	private WriteReport reporter;

	/**
	 * Constructor por defecto el cual inicializa la base de datos con los
	 * parametros por defecto
	 * 
	 * Si tenemos tiempo podemos mover todos los parametros de inicializacion a
	 * un fichero de properties
	 */
	public InsertMongo() {
		this.reporter = new WriteReportPort();

		mongo = new MongoClient("localhost", 27017);

		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("Citizens");
		DBCollection users = db.getCollection("users");
		users.createIndex(new BasicDBObject("id", 1), new BasicDBObject("unique", true));
		this.users = users;
	}

	public InsertMongo(DBCollection users) {
		this.reporter = new WriteReportPort();

		this.users = users;
	}

	@Override
	public void insert(Citizen c) {

		BasicDBObject document = new BasicDBObject();
		document.put("firstName", c.getName());
		document.put("lastName", c.getlastName());
		document.put("email", c.getEmail());
		document.put("password", c.getPassword());
		document.put("dateOfBirth", c.getID());
		document.put("address", c.getAddress());
		document.put("nationality", c.getNationality());
		document.put("id", c.getID());
		document.put("nif", c.getNIF());
		document.put("pollingStation", c.getpollingStation());
		try {
			users.insert(document);
		} catch (DuplicateKeyException me) {
			reporter.report(me, "Fallo al insertar en la base de datos: La clave introducida ya está en uso");
		} catch (MongoException me) {
			reporter.report(me, "Fallo al insertar en la base de datos");
		}
	}

	public void resetDatabase() {

	}

}
