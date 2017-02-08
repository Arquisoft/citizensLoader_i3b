package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReadList implements ReadList {
	Set<Citizen> census;
	private static final String passCharacters = "0123456789ABCDEFGHIJKLMNOP"
			+ "QRSTUVWXYZabcdefghijklmnopqrstuvwxyz$-_¡!?¿@";
	private static final int passLength = 12;

	@Override
	public void parse(String ruta) {
		POIFSFileSystem fs;
		HSSFWorkbook wb = null;
		HSSFSheet sheet;
		HSSFRow row;
		HSSFCell cell;
		try {
			fs = new POIFSFileSystem(new FileInputStream(new File(ruta)));
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			census = new HashSet<Citizen>();

			int rows = sheet.getPhysicalNumberOfRows();

			int cols = 9; // Nombre/Apellidos/Email/Fecha
							// nacimiento/Dirección/Nacionalidad/DNI/NIF/Polling
							// code

			String[] data = new String[cols];
			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							data[c] = cell.toString();
						}
					}
				}
				Citizen cit = new Citizen(data);
				createPassword(cit);
				census.add(cit);
			}
			
			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	private void createPassword(Citizen cit) {
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(passLength);
		for (int i = 0; i < passLength; i++) {
			sb.append(passCharacters
					.charAt(rnd.nextInt(passCharacters.length())));
		}
		cit.setPassword(sb.toString());
	}

}
