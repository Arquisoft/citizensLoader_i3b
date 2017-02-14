package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.database.SingletonDatabaseManager;

public class DatabaseManagerTest {

	@Test
	public void testGetinstance() {
		assertNotNull(SingletonDatabaseManager.getInstance());
	}
	
	@Test
	public void testGetInsert() {
		assertNotNull(SingletonDatabaseManager.getInstance().getInsertMongo());
	}

}