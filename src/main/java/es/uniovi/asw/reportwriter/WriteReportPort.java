package es.uniovi.asw.reportwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * Implementación de WriteReport que escribe en un archivo de texto los errores
 * detectados tanto al parsear el archivo excel como errores relacionados con la
 * base de datos (insertar duplicados, fallos de runtime en la BBDD etc)
 * 
 * Se crea un archivo de Log diario, y todos los errores que se den a lo largo
 * del día se especificarán en dicho archivo. El nombre del archivo es la fecha
 * del día del cual guarda los errores
 * 
 * @author Gonzalo de la Cruz Fernández - UO244583
 *
 */
public class WriteReportPort implements WriteReport {

	private File archivo;
	private SimpleDateFormat formatoNombreArchivo = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
	private SimpleDateFormat formatoFechaHoraReport = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

	/**
	 * Constructor el cual en función de la fecha de hoy busca el archivo en el
	 * cual tiene que escribir los errores
	 * 
	 * Si aun no se ha creado el archivo de hoy, se encarga de crearlo y su
	 * nombre sera la fecha del día de hoy
	 */
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
	/**
	 * Metodo que se utiliza en el parser para notificar los errores producidos
	 * al leer el archivo correspondiente.
	 * 
	 * Escribe en el archivo correspondiente al dia de hoy el error indicando el
	 * momento en el que se produjo,el archivo que se estaba parseando y la
	 * descripcion del error
	 * 
	 * @param mensajeDeError
	 *            - Descripcion del error producido
	 * 
	 * @param archivo
	 *            - archivo que se estaba parseando cuando se produjo el error
	 * 
	 */
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
	/**
	 * Metodo que se utiliza cuando se producen errores realizando operaciones
	 * en la base de datos
	 * 
	 * 
	 * @param e
	 *            - excepcion producida
	 *
	 * @param mensajeDeError
	 *            - descripcion del error producido
	 */
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
