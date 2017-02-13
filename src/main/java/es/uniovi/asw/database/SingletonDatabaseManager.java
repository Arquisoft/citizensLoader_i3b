package es.uniovi.asw.database;

import com.mongodb.DB;
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
		insertM = new InsertMongo(db);
	}

	public static SingletonDatabaseManager getInstance() {
		if (instance == null) {
			instance = new SingletonDatabaseManager();
		}
		return instance;
	}

	public InsertDB getInsertMongo() {
		return insertM;
	}
}
