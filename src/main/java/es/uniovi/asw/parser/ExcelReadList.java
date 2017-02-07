package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReadList implements ReadList {

	@Override
	public void parse(String ruta) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(
					new FileInputStream(new File(ruta)));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows = sheet.getPhysicalNumberOfRows();

			int cols = 6; // Nombre/Apellidos/Email/Fecha
							// nacimiento/Dirección/Nacionalidad/DNI

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							// TODO
						}
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

}
