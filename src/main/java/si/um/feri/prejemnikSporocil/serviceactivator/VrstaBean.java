package si.um.feri.prejemnikSporocil.serviceactivator;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/test"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class VrstaBean implements MessageListener {

	@EJB
	Zrno ejb;
	
	public void onMessage(Message message) {
		System.out.println("[VrstaBean] Prejeto Sporo�ilo! "+message);
		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println(tm.getText());
				
				if (tm.getText().equals("DAJMO!"))
					ejb.metoda();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
