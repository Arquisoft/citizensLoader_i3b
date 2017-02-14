package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorTest.class, ExcelParseTest.class, InsertMongoTest.class, PasswordTest.class })
public class AllTests {

}
