package si.um.feri.aiv.vao;

import java.text.SimpleDateFormat;
import java.io.Serializable;

import si.um.feri.aiv.vmesni_nivo.Email;
import si.um.feri.aiv.opazovalci.*;

import java.util.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Person implements Serializable {

	public Person() {
		this("", "", "", "", "", null);
	}

	public Person(String ime, String priimek, String email, String datum, String besedilo, Doctor patientsDoctor) {
		this.email = email;
		this.name = ime;
		this.surname = priimek;
		timestamp = new GregorianCalendar();
		this.date = datum;
		this.text = besedilo;
		this.patientsDoctor = patientsDoctor;
	}

	public Person(String ime, String priimek, String email, String datum, String besedilo, Doctor patientsDoctor, int id) {
		this.email = email;
		this.name = ime;
		this.surname = priimek;
		timestamp = new GregorianCalendar();
		this.date = datum;
		this.text = besedilo;
		this.patientsDoctor = patientsDoctor;
	}

	@NotBlank
	private String email;
	private String name;
	private String surname;
	private Calendar timestamp;
	private String date;
	private String text;
	private Doctor patientsDoctor;
	private int id;
	private List<Visit> visitsList;

	private List<IObserver> patientsObservers1 = new ArrayList<>();
	private List<IObserver> patientsObservers2 = new ArrayList<>();

	public void addPatientsObserver1(IObserver p) {
		patientsObservers1.add(p);
	}

	public void callPatientsObserver1(Person p) {
		for (IObserver i : patientsObservers1) {
			i.update(p);
		}
	}

	public void createObserver1(Person p) {
		p.addPatientsObserver1(new PatientObserver1());
		p.callPatientsObserver1(p);
	}

	public void addPatientsObserver2(IObserver p) {
		patientsObservers2.add(p);
	}

	public void callPatientsObserver2(Doctor p) {
		for (IObserver i : patientsObservers2) {
			i.update(this, p);
		}
	}

	public void createObserver2(Person p){
		p.addPatientsObserver2(new PatientObserver1());
		p.callPatientsObserver2(p.getPatientsDoctor());
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

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@ManyToOne
	public Doctor getPatientsDoctor() {
		return patientsDoctor;
	}

	public void setPatientsDoctor(Doctor patientsDoctor) {
		this.patientsDoctor = patientsDoctor;
	}

	@OneToMany
	public List<Visit> getVisitsList() {
		return visitsList;
	}

	public void setVisitsList(List<Visit> visitsList) {
		this.visitsList = visitsList;
	}

	@JsonbTransient
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy HH:mm:ss");

	@Override
	public String toString() {
		if (getPatientsDoctor() != null) {
			return name + " " + surname + " (" + email + "); vpis: " + sdf.format(timestamp.getTime()) + "; Datum rojstva: " + date + "; Opomba: "
					+ text + ";  Pacientov zdravnik: " + getPatientsDoctor().getName() + " " + getPatientsDoctor().getSurname();
		} else {
			return name + " " + surname + " (" + email + "); vpis: " + sdf.format(timestamp.getTime()) + "; Datum rojstva: " + date + "; Opomba: "
					+ text;
		}
	}

}