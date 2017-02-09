package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author Oriol
 * Excel parser.
 */
public class ExcelReadList extends AbstractReadList {
	
	@Override
	public void doParse(String ruta) {
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
				if(census.contains(cit) /* || already in db*/) {
					//TODO generate log.
				} else {
					census.add(cit);
				}
			}
			
			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

}
