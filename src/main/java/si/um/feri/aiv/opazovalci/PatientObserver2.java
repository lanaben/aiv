package si.um.feri.aiv.opazovalci;

import si.um.feri.aiv.vao.Person;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vmesni_nivo.Email;

import java.util.logging.Logger;

public class PatientObserver2 implements IObserver {
	
	Logger log = Logger.getLogger(PatientObserver2.class.toString());
	
	@Override
	public void update(Person p, Doctor d) {
		try {
			String content = "Vaš dodeljen zdravnik je bil spremenjen. Vaš stari zdravnik: "+ d;
			Email email = new Email(p.getEmail(), "mihec.korosec@gmail.com", "Spremenjen zdravnik!", content);
			email.send();
			log.info("email z vsebino spremenjenega zdravnika poslan");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Person p) {}
}