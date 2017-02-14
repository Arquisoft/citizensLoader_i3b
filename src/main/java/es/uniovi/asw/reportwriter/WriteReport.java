package es.uniovi.asw.reportwriter;

import java.io.FileInputStream;

public interface WriteReport {
	void report(FileInputStream file, String error);

	void report(Exception e, String mensajeDeError);
}
