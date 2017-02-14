package es.uniovi.asw.reportwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WriteReportPort implements WriteReport {

	private File archivo;
	private SimpleDateFormat formatoNombreArchivo = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
	private SimpleDateFormat formatoFechaHoraReport = new SimpleDateFormat("dd-MM-yyyy HM:mm:ss", Locale.getDefault());

	public WriteReportPort() {

		Date fechaActual = new Date();
		String nombreFichero = formatoNombreArchivo.format(fechaActual);
		this.archivo = new File(nombreFichero);

		try {
			archivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void report(FileInputStream source, String error) {

	}

	@Override
	public void report(Exception e, String mensajeDeError) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
			writer.write("Fecha: " + formatoFechaHoraReport.format(new Date()));
			writer.newLine();
			writer.write("Error: " + mensajeDeError);
			writer.newLine();
			writer.write("Mensaje excepcion: " + e.getMessage());
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
