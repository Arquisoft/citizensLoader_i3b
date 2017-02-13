package es.uniovi.asw.parser.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.lettergenerators.ConsoleLetterGenerator;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;
import es.uniovi.asw.parser.parserutil.PasswordGenerator;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.reportwriter.WriteReportPort;

/**
 * @author Oriol
 * Template, concrete parsers (Excel/Word/txt/...) will override "doParse".
 */
public abstract class AbstractReadList implements ReadList {

	protected Set<Citizen> census;
	private LetterGenerator letterGen;
	protected WriteReport wReport;
	
	public AbstractReadList() {
		this.letterGen = new ConsoleLetterGenerator();
		this.wReport = new WriteReportPort();
	}
	
	public AbstractReadList(LetterGenerator letterGenerator) {
		this.letterGen = letterGenerator;
		this.wReport = new WriteReportPort();
	}
	
	@Override
	public Set<Citizen> parse(String ruta) {
		try {
			doParse(new FileInputStream(ruta));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PasswordGenerator.createPasswords(census);
		generateLetters();
		return census;
	}

	protected abstract void doParse(FileInputStream fileInputStream);
	
	private void generateLetters() {
		for(Citizen c: census) {
			letterGen.generatePersonalLetter(c);
		}
	}

}
