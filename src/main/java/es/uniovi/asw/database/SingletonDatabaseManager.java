package es.uniovi.asw.database;

/**
 * 
 * @author Oriol
 *
 */
public class SingletonDatabaseManager {
	private static SingletonDatabaseManager instance;
	private InsertDB insertM;

	private SingletonDatabaseManager() {
		insertM = new InsertMongo();
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
