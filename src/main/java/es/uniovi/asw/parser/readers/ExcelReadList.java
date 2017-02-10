package es.uniovi.asw.parser.readers;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import es.uniovi.asw.parser.Citizen;

/**
 * @author Oriol Excel parser.
 */
public class ExcelReadList extends AbstractReadList {

	@Override
	public void doParse(String ruta) {
		POIFSFileSystem fs;
		HSSFWorkbook wb = null;
		HSSFSheet sheet;
		HSSFRow row;
		try {
			fs = new POIFSFileSystem(new FileInputStream(new File(ruta)));
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			census = new HashSet<Citizen>();

			int rows = sheet.getPhysicalNumberOfRows();

			int cols = 9; // Nombre/Apellidos/Email/Fecha
							// nacimiento/Dirección/Nacionalidad/DNI/NIF/Polling
							// code

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);

				String[] data = parseRow(row, cols);

				Citizen cit = null;
				if (data != null) {
					cit = new Citizen(data);
				} else {
					// TODO error row or cell empty, generate log.
				}

				if (census.contains(cit) /* || already in db */) {
					// TODO generate log.
				} else {
					census.add(cit);
				}
			}

			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	private String[] parseRow(HSSFRow row, int cols) {
		HSSFCell cell;
		String[] data = new String[cols];

		if (row != null) {
			for (int c = 0; c < cols; c++) {
				cell = row.getCell((short) c);
				if (cell != null) {
					data[c] = cell.toString();
				} else {
					return null;
				}
			}
			return data;
		}
		return null;
	}

}
