package si.um.feri.aiv.remote_vmesnik;


import java.rmi.Naming;

import si.um.feri.aiv.jsf.AppJSF;

public class TestEjbClient {

	public static void main(String[] args) throws Exception {
		
		AppJSF c = (AppJSF)Naming.lookup("//localhost:2020/calc");
        // new CalculatorFactory().createCalculator();
				//new Calculator();

		c.connectPersonDoctor("lana.benedicic@gmail.com", "Email Zdravnik");


		System.out.println("Server started!");
	}
	
}