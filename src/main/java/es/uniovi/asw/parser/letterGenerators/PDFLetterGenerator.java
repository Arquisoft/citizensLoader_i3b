package es.uniovi.asw.parser.letterGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import es.uniovi.asw.parser.Citizen;

public class PDFLetterGenerator implements LetterGenerator {

	@Override
	public String generatePersonalLetter(Citizen c) {
		Document document = new Document();

		try {

			PdfWriter.getInstance(document,
					new FileOutputStream(new File(c.getDni()+".pdf")));

			// open
			document.open();
			
			Font f = new Font();
			f.setStyle(Font.BOLD);

			Paragraph p = new Paragraph();
			p.setFont(f);
			p.add("To: "+c.getEmail());

			document.add(p);

			Paragraph p2 = new Paragraph();
			p2.setFont(f);
			p2.add("Subject: Login data\n");

			document.add(p2);
			
			Paragraph main = new Paragraph();
			main.add("Mr/Mrs "+ c.getName() +" "+ c.getSurname()+",\n"
					+ "Your login data has been generated:\n"
					+ "\tUsername: "+c.getDni()+"\n"
					+ "\tPassword: "+c.getPassword()+"\n");

			document.add(main);

			// close
			document.close();

			System.out.println(c.getDni() +"letter sent.");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
