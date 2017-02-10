package es.uniovi.asw.parser;

import es.uniovi.asw.parser.readers.ExcelReadList;

/**
 * @author uo245303
 * Parser Manager
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

	public ExcelReadList getDefaultExcelReadList() {
		return excelRL;
	}
}
