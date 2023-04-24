package si.um.feri.aiv.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

	public static void main(String[] args) throws Exception {
    
		CalculatorImpl c=new CalculatorImpl();

    LocateRegistry.createRegistry(2020);
    
		Naming.rebind("//localhost:2020/calc", c);

    System.out.println("Server started!");
    
	}
	
}