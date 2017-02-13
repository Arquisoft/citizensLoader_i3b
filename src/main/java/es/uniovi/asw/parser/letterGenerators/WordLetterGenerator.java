package es.uniovi.asw.parser.letterGenerators;

import es.uniovi.asw.parser.Citizen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordLetterGenerator implements LetterGenerator {
	
	/**
	 * @author Adrian
	 * 
	 * Generates a new word document into which it writes
	 * the data of a given citizen.
	 */
	@Override
	public void generatePersonalLetter(Citizen c) {
		// Blank Document
		XWPFDocument document = new XWPFDocument();

		// Write the Document in file system
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("createdocument.docx"));
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			
			//Add text to the document 
			run.setText("Mr/Mrs " + c.getName() + " " + c.getlastName() + ",\n"
					+ "Your login data has been generated:\n" + "\tUsername: "
					+ c.getEmail() + "\n" + "\tPassword: " + c.getPassword()
					+ "\n");
			document.write(out);
			out.close();
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
