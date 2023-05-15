// package si.um.feri.aiv.client;

// import javax.naming.InitialContext;

// import jakarta.jms.ConnectionFactory;
// import jakarta.jms.DeliveryMode;
// import jakarta.jms.Destination;
// import jakarta.jms.JMSContext;
// import jakarta.jms.*;

// public class ClientMessages {
// 	public static void main(String[] args) throws Exception {
		
// 		InitialContext ictx = InitialContextFactory.getInitialContext();
// 		Destination destination = (Destination) ictx.lookup("jms/queue/test");
// 		ConnectionFactory factory = (ConnectionFactory) ictx.lookup("jms/RemoteConnectionFactory");
// 		JMSContext ctx = factory.createContext();
// 		JMSProducer producer = ctx.createProducer();

// 		producer.send(destination,"Ojla!");
// 		producer.send(destination,"Pa spet.");
// 		producer.send(destination,"Pa se enkrat :).");

// 		//sporocilo, ki ni trajno
// 		Message m=ctx.createTextMessage("lana.benedicic@gmail.com");
// 		m.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
// 		m.setJMSPriority(3);
// 		m.setJMSExpiration(10000);
// 		producer.send(destination,m);

// 		Message m2=ctx.createTextMessage("benedicic@gmail.com");
// 		m2.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
// 		m2.setJMSPriority(3);
// 		m2.setJMSExpiration(10000);
// 		producer.send(destination,m2);

// 		ctx.close();
		
// 	}
// }

