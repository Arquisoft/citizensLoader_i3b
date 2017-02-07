package es.uniovi.asw.parser;

/**
 * @author uo245303
 *
 */
public class SingletonParser {
	private static SingletonParser instance;
	private ExcelReadList excelRL;

	private SingletonParser() {
		excelRL = new ExcelReadList();
	}

	public static SingletonParser getInstance() {
		if (instance == null) {
			instance = new SingletonParser();
		}
		return instance;
	}

	public ExcelReadList getExcelRL() {
		return excelRL;
	}
}
