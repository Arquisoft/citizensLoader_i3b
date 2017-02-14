package es.uniovi.asw.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoException;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportPort;

public class InsertMongo implements InsertDB {

	private DBCollection users;
	private WriteReport reporter;

	/**
	 * Constructor that should be used.
	 * Creation of the database should be in the manager.
	 * @param users - mongo collection
	 */
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
			reporter.report(me,"Fallo al insertar en la base de datos: "
					+ "La clave introducida ya está en uso");
		} catch (MongoException me) {
			reporter.report(me, "Fallo al insertar en la base de datos");
		}
	}

}
