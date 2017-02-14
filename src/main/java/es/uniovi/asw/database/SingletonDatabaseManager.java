package es.uniovi.asw.database;

/**
 * 
 * @author Oriol
 *
 */
public class SingletonDatabaseManager {
	private static SingletonDatabaseManager instance;
	private InsertDB insertM;

	/**
	 * Todo lo que había en esta clase relacionado con Mongo lo movi a la clase
	 * InsertMongo
	 * 
	 * Esta clase no debería conocer nada de mongo para poder ser reutilizada en
	 * caso de que usasemos otro tipo de BBDD
	 */

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
