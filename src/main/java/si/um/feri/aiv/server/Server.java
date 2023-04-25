package si.um.feri.aiv.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import si.um.feri.aiv.ejb.PersonDao;
import si.um.feri.aiv.ejb.PersonDaoRemote;
import si.um.feri.aiv.ejb.PersonMemoryDao;
import si.um.feri.aiv.jsf.AppJSF;
import si.um.feri.aiv.jsf.AppJSFBean;


public class Server extends UnicastRemoteObject {
  
  public Server() throws RemoteException {

  }

  @EJB
	public static PersonDaoRemote daoPerson = PersonMemoryDao.getInstance();

	public static void main(String[] args) throws Exception {
    
		// CalculatorImpl c=new CalculatorImpl();
 

    System.out.println(daoPerson.getAll().size());

    LocateRegistry.createRegistry(2020);

   
		// Naming.rebind("//localhost:2020/calc", person);

    System.out.println("Server started!");
    
	}
	
}