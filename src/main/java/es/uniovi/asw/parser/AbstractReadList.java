package es.uniovi.asw.parser;

import java.util.Set;

import es.uniovi.asw.parser.letterGenerators.ConsoleLetterGenerator;
import es.uniovi.asw.parser.letterGenerators.LetterGenerator;
import es.uniovi.asw.parser.parserUtil.PasswordGenerator;

/**
 * @author Oriol
 * Template, concrete parsers (Excel/Word/txt/...) will override "doParse".
 */
public abstract class AbstractReadList implements ReadList {

	Set<Citizen> census;
	private LetterGenerator letterGen;
	
	public AbstractReadList() {
		this.letterGen = new ConsoleLetterGenerator();
	}
	
	public AbstractReadList(LetterGenerator letterGenerator) {
		this.letterGen = letterGenerator;
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
