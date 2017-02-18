package es.uniovi.asw;

import java.util.Set;

import es.uniovi.asw.database.CitizenDao;
import es.uniovi.asw.database.MongoPersistanceFactory;
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
		CitizenDao dao = MongoPersistanceFactory.getCitizenDao();
		for (Citizen c : census) {
			dao.insert(c);
		}
	}
}
