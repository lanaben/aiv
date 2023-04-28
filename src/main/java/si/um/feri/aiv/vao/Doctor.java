package si.um.feri.aiv.vao;

import java.util.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Doctor {
	
	public Doctor() {
		this("", "","", 0.0);
	}
	

	public Doctor(String ime, String priimek, String email, Double steviloPac) {
		this.name = ime;
		this.surname = priimek;
		this.email = email;
		this.numPatient = steviloPac;
		String uniqueID = UUID.randomUUID().toString();
		this.id = uniqueID;
	}
	
	@NotBlank
	private String name;
	private String surname;
	private String email;
	private Double numPatient;
	private String id;
	private List<Person> petientsList;

	@OneToMany
	public List<Person> getpetientsList() {
		return petientsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getNumPatient() {
		return numPatient;
	}

	public void setNumPatient(Double numPatient) {
		this.numPatient = numPatient;
	}
	
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.name + " " + this.surname + " ;" + this.email + "; Število možnih pacientov: " + this.numPatient + " ";
	}
	
}