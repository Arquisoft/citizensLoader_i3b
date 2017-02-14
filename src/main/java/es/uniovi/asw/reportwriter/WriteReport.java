package es.uniovi.asw.reportwriter;

public interface WriteReport {
	void report(String mensajeDeError, String archivo);

	void report(Exception e, String mensajeDeError);
}
