package si.um.feri.aiv.vao;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Visit implements Serializable {
	
	private Person visitsPerson;
	private Doctor visitsDoctor;
  private String date;
	private String time;
  private String comment;
	private String medicine;
	private int id;

	public Visit() {
		this( null, null, "", "", "", "");
	}

  public Visit(Person visitsPerson, Doctor visitsDoctor, String date, String time, String comment, String medicine) {
		this.visitsPerson = visitsPerson;
		this.visitsDoctor = visitsDoctor;
		this.date = date;
		this.time = time;
		this.comment = comment;
		this.medicine = medicine;
	}

	public Visit(Person visitsPerson, Doctor visitsDoctor, String date, String time, String comment, String medicine, int id) {
		this.visitsPerson = visitsPerson;
		this.visitsDoctor = visitsDoctor;
		this.date = date;
		this.time = time;
		this.comment = comment;
		this.medicine = medicine;
	}

	
	public void setVisitsDoctor(Doctor visitsDoctor) {
		this.visitsDoctor = visitsDoctor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Doctor getVisitsDoctor() {
		return visitsDoctor;
	}


	public void setVisitsPerson(Person visitsPerson) {
		this.visitsPerson = visitsPerson;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Person getVisitsPerson() {
		return visitsPerson;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
			return this.getVisitsPerson().getEmail() + " " + this.getVisitsDoctor().getEmail() + " " + date + " " + time + " " + comment + " " + medicine;
		}
}
