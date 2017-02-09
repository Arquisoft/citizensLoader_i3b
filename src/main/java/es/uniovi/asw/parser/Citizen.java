package es.uniovi.asw.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Oriol
 * Class use to represent the citizens and parse their data.
 */
public class Citizen {
	private String name;
	private String surname;
	private String email;
	private Date dob;
	private String address;
	private String dni;
	private String password;
	private String nationality;
	private String nif;
	private int ps;

	public Citizen(String name, String surname, String email, String dob,
			String address, String nationality, String dni, String NIF,
			String pollingStation) {

		this.name = name;
		this.surname = surname;
		this.email = email;
		setDob(dob);
		this.address = address;
		this.nationality = nationality;
		this.dni = dni;
		this.nif = NIF;
		this.ps = Integer.parseInt(pollingStation);
	}

	private void setDob(String dob) {
		DateFormat format = new SimpleDateFormat("d/M/y");
		Date date = null;
		try {
			date = format.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dob = date;
	}

	public Citizen(String[] data) {
		this.name = data[0];
		this.surname = data[1];
		this.email = data[2];
		setDob(data[3]);
		this.address = data[4];
		this.nationality = data[5];
		this.dni = data[6];
		this.nif = data[7];
		this.ps = Integer.parseInt(data[8]);
	}

	public String getNationality() {
		return nationality;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public Date getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	public String getDni() {
		return dni;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pw) {
		this.password = pw;
	}

	public String getNif() {
		return nif;
	}

	public int getPs() {
		return ps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citizen other = (Citizen) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [name=" + name + ", surname=" + surname + ", email="
				+ email + ", dob=" + dob + ", address=" + address + ", dni="
				+ dni + ", password=" + password + ", nationality="
				+ nationality + ", nif=" + nif + ", ps=" + ps + "]";
	}

}
