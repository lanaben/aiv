// package si.um.feri.prejemnikSporocil;

// import jakarta.ejb.ActivationConfigProperty;
// import jakarta.ejb.EJB;
// import jakarta.ejb.MessageDriven;
// import jakarta.jms.Message;
// import jakarta.jms.MessageListener;
// import jakarta.jms.TextMessage;
// import java.util.logging.Logger;

// @MessageDriven(activationConfig = {
// 		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
// 		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/test"),
// 		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

// public class prejemnik implements MessageListener {

// 	Logger log = Logger.getLogger(prejemnik.class.toString());

// 	@EJB
//   Dalje ejb;

// 	public void onMessage(Message message) {
// 		if (message instanceof TextMessage) {
// 			TextMessage tm = (TextMessage) message;
// 			try {
// 				log.info("[prejemnik] SPOROCILO: "+tm.getText());
// 				ejb.dajmo("gnjavim");
// 			} catch (Exception e) {
// 				e.printStackTrace();
// 			}
// 		} else {
// 			log.info("Prispelo je neznano sporocilo.");
// 		}
// 	}

// }
