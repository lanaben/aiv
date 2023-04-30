package si.um.feri.aiv.jsf;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

import javax.sound.midi.MidiDevice.Info;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.aiv.vao.Person;
import si.um.feri.aiv.ejb.DoctorDao;
import si.um.feri.aiv.ejb.DoctorMemoryDao;
import si.um.feri.aiv.ejb.PersonDao;
import si.um.feri.aiv.ejb.PersonMemoryDao;
import si.um.feri.aiv.vao.Doctor;

import si.um.feri.aiv.vmesni_nivo.Email;
import si.um.feri.aiv.opazovalci.PatientObserver1;

@Named("app")
@SessionScoped
public class AppJSFBean implements Serializable {

	

	private static final long serialVersionUID = -8979220536758073133L;

	Logger log = Logger.getLogger(AppJSFBean.class.toString());
	
	
	
	@EJB
	private PersonDao daoPerson = PersonMemoryDao.getInstance();
	
	private Person selectedPerson = new Person();
	
	private static String selectedEmailPerson;

	Boolean isSavePerson;

	private String selectedPatientsDoctor;
	
	private Person oldPerson = new Person();
	
	
	public List<Person> getAllPeople() throws Exception {
		return daoPerson.getAll();
	}
	
	public String savePerson() throws Exception {
		
		
		if(oldPerson.getEmail() != "") {
			selectedPerson.createObserver2(selectedPerson);
		}
		
		int index = 0;
		
		List<Doctor> doctors = daoDoctor.getAll();
		
		if(this.selectedPatientsDoctor != null) {
			for(int i = 0; i < doctors.size(); i++) {
				if(this.selectedPatientsDoctor.equals(doctors.get(i).getId())) {
					index = i;
				}
			}
			
			Doctor selectedDoctor = doctors.get(index);
			
			List<Person> listPatients = getAllPeople();
			double count = 0;

			if(selectedDoctor.getNumPatient() == 0) {
				Email email = new Email(selectedPerson.getEmail(), "lana.benedicic@gmail.com", "Zdravnik ni izbran", "Kvota zdravnika je že dosežena. Izberite drugega zdravnika.");
				email.send();
			}
			
			for(int i = 0; i < listPatients.size(); i++) {
					if (listPatients.get(i).getPatientsDoctor() != null && listPatients.get(i).getPatientsDoctor().equals(selectedDoctor)) {
						count++;
						
						if (count == selectedDoctor.getNumPatient()) {
							//send email to patient doctor cant be chosen
							//break
							Email email = new Email(selectedPerson.getEmail(), "lana.benedicic@gmail.com", "Zdravnik ni izbran", "Kvota zdravnika je že dosežena. Izberite drugega zdravnika.");
							email.send();
							break;
						}
					}
			}
			
			if (count < selectedDoctor.getNumPatient()) {

				//set patients doctor
				selectedPerson.setPatientsDoctor(doctors.get(index));
				daoPerson.save(selectedPerson, isSavePerson);
				
				//email to patient and doctor it was chosen
				Email emailDoctor = new Email(doctors.get(index).getEmail(), "lana.benedicic@gmail.com", "Pacient je bil dodan", "Dobili ste novega pacienta");
				emailDoctor.send();
			}
			
			selectedPerson.createObserver1(selectedPerson);
		
			
		} else if (this.selectedPatientsDoctor == null){
			daoPerson.save(selectedPerson, isSavePerson);
		}
		return "all";
	}
	
	public void deletePerson(Person o) throws Exception {
		daoPerson.delete(o.getEmail());
	}

	public void setSelectedEmailPerson(String email) throws Exception {
		selectedEmailPerson = email;
		selectedPerson = daoPerson.find(email);
		if(selectedPerson.getEmail() == "") {
			selectedPerson = new Person();
			oldPerson = new Person();
			isSavePerson = true;
			log.info("issaveperson true");
		}
		else if(selectedPerson.getEmail() != "") {
			oldPerson = selectedPerson;
			log.info("issaveperson false");
			isSavePerson = false;
		}
	}
	
	public String getSelectedEmailPerson() {
		return selectedEmailPerson;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}
	
	public String getSelectedPatientsDoctor() {
        return selectedPatientsDoctor;
    }
	public void setSelectedPatientsDoctor(String selectedPatientsDoctor) {
        this.selectedPatientsDoctor = selectedPatientsDoctor;
    }
	
	@EJB
	private DoctorDao daoDoctor = DoctorMemoryDao.getInstance();
	
	private Doctor selectedDoctor = new Doctor();
	
	private String selectedEmailDoctor;

	Boolean isSaveDoctor;

	public List<Doctor> getAllDoctors() throws Exception {
		return daoDoctor.getAll();
	}
	
	public String saveDoctor() throws Exception {
		daoDoctor.save(selectedDoctor, isSaveDoctor);
		return "all";
	}
	
	public void deleteDoctor(Doctor i) throws Exception {
		daoDoctor.delete(i.getEmail());
	}

	public void setSelectedEmailDoctor(String email) throws Exception {
		selectedEmailDoctor = email;
		selectedDoctor = daoDoctor.find(email);
		if(selectedDoctor.getEmail() == "") {
			selectedDoctor = new Doctor();
			isSaveDoctor = true;
		}
		else if(selectedDoctor.getEmail() != "") {
			isSaveDoctor = false;
		}
	}
	
	public String getSelectedEmailDoctor() {
		return selectedEmailDoctor;
	}

	public Doctor getSelectedDoctor() {
		return selectedDoctor;
	}

	public void setSelectedDoctor(Doctor selectedDoctor) {
		this.selectedDoctor = selectedDoctor;
	}
	
	
	public List<Person> getPatientsNoDoctors () throws Exception {
		
		List<Person> people = getAllPeople();
		
		List<Person> patientsWithNoDoc = new ArrayList<Person>();
		
		for(int i = 0; i < people.size(); i++) {
			if (people.get(i).getPatientsDoctor() == null) {
				patientsWithNoDoc.add(people.get(i));
				
			}
			else {
				continue;
			}
		}
		return patientsWithNoDoc;
	}
}
