package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExcelParseTest.class, CitizenDaoMongoTest.class,
		PasswordTest.class, CitizenTest.class, SingletonParserTest.class,
		PDFGeneratorTest.class, WordGeneratorTest.class,
		PersistanceFactoryTest.class })

public class AllTests {

}
