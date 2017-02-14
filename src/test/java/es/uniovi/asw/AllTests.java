package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExcelParseTest.class, InsertMongoTest.class,
	PasswordTest.class, CitizenTest.class, DatabaseManagerTest.class,
	SingletonParserTest.class})
public class AllTests {

}
