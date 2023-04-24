package si.um.feri.aiv.remote_vmesnik;


import java.rmi.Naming;
import si.um.feri.aiv.server.Calculator;
import si.um.feri.aiv.server.CalculatorImpl;

public class TestEjbClient {

	public static void main(String[] args) throws Exception {
		
		Calculator c = (Calculator)Naming.lookup("//localhost:2020/calc");
        // new CalculatorFactory().createCalculator();
				//new Calculator();
		
		System.out.println(c.add(4, 5));
		
		System.out.println(c.getHistory());
		
		System.out.println(c.getLastCalculation());


	}
	
}