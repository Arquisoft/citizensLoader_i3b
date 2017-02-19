package es.uniovi.asw.database;

public final class MongoPersistanceFactory {

	public static CitizenDao getCitizenDao() {
		return new CitizenDaoImplMongo();
	}

}
