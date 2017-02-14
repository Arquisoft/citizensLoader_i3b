package es.uniovi.asw.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * 
 * @author Oriol
 *
 */
public class SingletonDatabaseManager {
	private static SingletonDatabaseManager instance;
	private InsertDB insertM;
	private MongoClient mongo;

	private SingletonDatabaseManager() {
		mongo = new MongoClient("localhost", 27017);

		@SuppressWarnings("deprecation")
		DB db = mongo.getDB("Citizens");
		DBCollection users = db.getCollection("users");
		users.createIndex(new BasicDBObject("id", 1),
				new BasicDBObject("unique", true));
		
		insertM = new InsertMongo(users);
	}

	public static SingletonDatabaseManager getInstance() {
		if (instance == null) {
			instance = new SingletonDatabaseManager();
		}
		return instance;
	}

	/**
	 * Returns the class in charge of inserting users in the database.
	 * @return
	 */
	public InsertDB getInsertMongo() {
		return insertM;
	}
	
	/**
	 * closes the database client.
	 */
	public void closeClient() {
		mongo.close();
	}
}
