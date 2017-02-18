package allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import databaseTests.AllDatabaseTests;
import letterGeneratorsTest.AllGeneratorTests;
import parserTests.AllParserTests;

@RunWith(Suite.class)
@SuiteClasses({ AllDatabaseTests.class, AllGeneratorTests.class,
		AllParserTests.class })

public class AllTests {

}
