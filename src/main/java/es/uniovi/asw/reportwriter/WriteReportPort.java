package es.uniovi.asw.reportwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WriteReportPort implements WriteReport {

	private File archivo;
	private SimpleDateFormat formatoNombreArchivo = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
	private SimpleDateFormat formatoFechaHoraReport = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

	public WriteReportPort() {

		Date fechaActual = new Date();
		String nombreFichero = formatoNombreArchivo.format(fechaActual);
		this.archivo = new File(nombreFichero);

		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void report(String mensajeDeError, String archivo) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

			writer.append("Fecha y hora: " + formatoFechaHoraReport.format(new Date()));
			writer.newLine();
			writer.append("Nombre del archivo: " + archivo);
			writer.newLine();
			writer.append("Error: " + mensajeDeError);
			writer.newLine();
			writer.newLine();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void report(Exception e, String mensajeDeError) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

			writer.append("Fecha y hora: " + formatoFechaHoraReport.format(new Date()));
			writer.newLine();
			writer.append("Error: " + mensajeDeError);
			writer.newLine();
			writer.append("Mensaje excepcion: " + e.getMessage());
			writer.newLine();
			writer.newLine();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
