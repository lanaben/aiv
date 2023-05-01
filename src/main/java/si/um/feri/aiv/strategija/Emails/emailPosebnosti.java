package si.um.feri.aiv.strategija.Emails;

import java.util.logging.Logger;
import si.um.feri.aiv.strategija.visitsInterface;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Person;
import si.um.feri.aiv.vmesni_nivo.Email;

public class emailPosebnosti implements visitsInterface {

  Logger log = Logger.getLogger(emailPosebnosti.class.toString());

  @Override
  public void sendEmailVisits(Person p) {
    try {
			String content = "Imate zabeležen nov obisk. Zdravnik je vnesel posebnosti: ";
			Email email = new Email(p.getEmail(), "lana.benedicic@gmail.com", "Nov zabeležen obisk!", content);
			email.send();
      log.info("email z vsebino posebnosti poslan");
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
  
}
