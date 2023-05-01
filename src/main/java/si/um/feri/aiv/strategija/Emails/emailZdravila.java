package si.um.feri.aiv.strategija.Emails;

import si.um.feri.aiv.strategija.visitsInterface;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Person;

import si.um.feri.aiv.vmesni_nivo.Email;


public class emailZdravila implements visitsInterface {

  @Override
  public void sendEmailVisits(Person p) {
    try {
			String content = "Imate zabeležen nov obisk. Zdravnik je vnesel zdravila:";
			Email email = new Email(p.getEmail(), "lana.benedicic@gmail.com", "Nov zabeležen obisk!", content);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
  
}
