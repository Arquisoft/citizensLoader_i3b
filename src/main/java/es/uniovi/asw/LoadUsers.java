package es.uniovi.asw;

import java.util.Set;

import es.uniovi.asw.database.InsertDB;
import es.uniovi.asw.database.SingletonDatabaseManager;
import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.SingletonParser;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

	public static void main(String... ruta) {
		final LoadUsers runner = new LoadUsers();
		runner.run(ruta);
	}

	// TODO
	private void run(String... ruta) {
		if (ruta.length < 1) {
			System.err.println("Input the name of the file.");
			return;
		}
		
		ReadList rl = SingletonParser.getInstance().getDefaultExcelReadList();
		Set<Citizen> census = rl.parse(ruta[0]);
		
		insertCitizens(census);
		
		System.out.println("Users added successfully");
	}

	private void insertCitizens(Set<Citizen> census) {
		InsertDB db = SingletonDatabaseManager.getInstance().getInsertMongo();
		for(Citizen c: census) {
			db.insert(c);
		}
	}
}
