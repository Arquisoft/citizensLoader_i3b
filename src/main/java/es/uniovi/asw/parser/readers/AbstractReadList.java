package es.uniovi.asw.parser.readers;

import java.util.Set;

import es.uniovi.asw.database.InsertDB;
import es.uniovi.asw.database.InsertMongo;
import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.letterGenerators.ConsoleLetterGenerator;
import es.uniovi.asw.parser.letterGenerators.LetterGenerator;
import es.uniovi.asw.parser.parserUtil.PasswordGenerator;
import es.uniovi.asw.reportWriter.WriteReport;
import es.uniovi.asw.reportWriter.WriteReportPort;

/**
 * @author Oriol
 * Template, concrete parsers (Excel/Word/txt/...) will override "doParse".
 */
public abstract class AbstractReadList implements ReadList {

	Set<Citizen> census;
	private LetterGenerator letterGen;
	protected WriteReport wReport;
	protected InsertDB insertDB;
	
	public AbstractReadList() {
		this.letterGen = new ConsoleLetterGenerator();
		this.wReport = new WriteReportPort();
		this.insertDB = new InsertMongo();
	}
	
	public AbstractReadList(LetterGenerator letterGenerator) {
		this.letterGen = letterGenerator;
		this.wReport = new WriteReportPort();
		this.insertDB = new InsertMongo();
	}
	
	@Override
	public void parse(String ruta) {
		doParse(ruta);
		PasswordGenerator.createPasswords(census);
		generateLetters();
		//TODO Send data to db manager.
	}

	protected abstract void doParse(String ruta);
	
	private void generateLetters() {
		for(Citizen c: census) {
			letterGen.generatePersonalLetter(c);
		}
	}

}
