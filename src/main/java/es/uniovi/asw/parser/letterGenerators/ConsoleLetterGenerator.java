package es.uniovi.asw.parser.letterGenerators;

import es.uniovi.asw.parser.Citizen;

/**
 * 
 * @author Oriol
 * Default generator
 */
public class ConsoleLetterGenerator implements LetterGenerator{

	@Override
	public String generatePersonalLetter(Citizen c) {
		StringBuilder sb = new StringBuilder();
		sb.append("To: "+c.getEmail()+"\n");
		sb.append("Subject: Login data\n");
		sb.append("Mr/Mrs "+ c.getName() +" "+ c.getSurname()+",\n\n");
		sb.append("Your login data has been generated:\n");
		sb.append("\tUsername: "+c.getDni()+"\n");
		sb.append("\tPassword: "+c.getPassword()+"\n");
		System.out.println(c.getDni() +"letter sent.");
		return sb.toString();
	}

}