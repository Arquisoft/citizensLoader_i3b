package es.uniovi.asw.parser.readers;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.parser.Citizen;
import es.uniovi.asw.parser.lettergenerators.LetterGenerator;

/**
 * @author Oriol.
 * Excel parser.
 */
public class ExcelReadList extends AbstractReadList {

	public ExcelReadList() {
		super();
	}

	public ExcelReadList(LetterGenerator letterGenerator) {
		super(letterGenerator);
	}

	@Override
	public void doParse(FileInputStream file) {
		XSSFWorkbook wb = null;
		XSSFSheet sheet;
		XSSFRow row;
		try {

			wb = new XSSFWorkbook(OPCPackage.open(file));
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
					wReport.report(file, "Empty row nº" + r);
				}

				if (cit.getID() == null) {
					wReport.report(file, "Null DNI on row number " + r);
				} else if (census.contains(cit)) {
					wReport.report(file,
							"Duplicated citizen on row number " + r);
				} else {
					census.add(cit);
				}
			}

			wb.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private String[] parseRow(XSSFRow row, int cols) throws ParseException {
		XSSFCell cell;
		String[] data = new String[cols];

		if (row != null) {
			for (int c = 0; c < cols; c++) {
				cell = row.getCell((short) c);
				if (cell != null) {
					if (cell.getCellTypeEnum() == CellType.NUMERIC &&
							DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd/MM/yyyy");
						data[c] = sdf.format(cell.getDateCellValue());
					} else {
						data[c] = cell.toString();
					}
				}
			}
			return data;
		}
		return null;
	}

}
