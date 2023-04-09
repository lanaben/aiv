package si.um.feri.aiv.opazovalci;

import si.um.feri.aiv.vao.Person;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vmesni_nivo.Email;

import java.util.logging.Logger;

public class PatientObserver1 implements IObserver {
	
	Logger log = Logger.getLogger(PatientObserver1.class.toString());
	
	@Override
	public void update(Person p) {
		try {
			String content = "Dodeljen vam je bil nov zdravnik: "+ p.getPatientsDoctor().getName() + p.getPatientsDoctor().getSurname();
			Email email = new Email(p.getEmail(), "mihec.korosec@gmail.com", "Nov zdravnik!", content);
			email.send();
			log.info("email z vsebino dodeljenega zdravnika poslan");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Person p, Doctor d) {}
}