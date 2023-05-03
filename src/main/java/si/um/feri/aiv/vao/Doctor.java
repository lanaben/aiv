package si.um.feri.aiv.vao;

import java.io.Serializable;
import java.util.*;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Doctor implements Serializable {
	
	public Doctor() {
		this("", "","", 0);
	}
	

	public Doctor(String ime, String priimek, String email, int steviloPac) {
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
	private int numPatient;
	private String id;
	private List<Person> patientsList;
	private List<Visit> visitsList;

	@JsonbTransient
	@OneToMany
	public List<Person> getPatientsList() {
		return patientsList;
	}

	public void setPatientsList(List<Person> patientsList) {
		this.patientsList = patientsList;
	}

	@JsonbTransient
	@OneToMany
	public List<Visit> getVisitsList() {
		return visitsList;
	}

	public void setVisitsList(List<Visit> visitsList) {
		this.visitsList = visitsList;
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
	
	public int getNumPatient() {
		return numPatient;
	}

	public void setNumPatient(int numPatient) {
		this.numPatient = numPatient;
	}
	
	@JsonbTransient
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