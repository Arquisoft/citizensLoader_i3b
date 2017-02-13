package es.uniovi.asw.parser.readers;

import java.io.File;
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
	public void parse(String ruta) {
		doParse(new File(ruta));
		PasswordGenerator.createPasswords(census);
		generateLetters();
		//TODO Send data to db manager.
	}

	protected abstract void doParse(File file);
	
	private void generateLetters() {
		for(Citizen c: census) {
			letterGen.generatePersonalLetter(c);
		}
	}

}
