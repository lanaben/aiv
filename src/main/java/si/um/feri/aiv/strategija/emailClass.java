package si.um.feri.aiv.strategija;

import si.um.feri.aiv.strategija.Emails.emailPosebnosti;
import si.um.feri.aiv.strategija.Emails.emailZdravila;
import si.um.feri.aiv.vao.Person;
import si.um.feri.aiv.vao.Visit;

import java.util.logging.Logger;

public class emailClass {

  Logger log = Logger.getLogger(emailClass.class.toString());

  visitsInterface vi;

  public emailClass(visitsInterface vrstaObiskaEmail) {
		vi = vrstaObiskaEmail;
	}

  public void izberiVrstoMaila(Visit visitMail, Person p) {
		if (visitMail.getComment() != null) {
      log.info("funk izberiVrstoMaila ena");
			vi = new emailPosebnosti();
      vi.sendEmailVisits(p); 
		}
		if (visitMail.getMedicine() != null ) {
      log.info("funk izberiVrstoMaila dve");
			vi = new emailZdravila();
      vi.sendEmailVisits(p); 
		}
	}
}
