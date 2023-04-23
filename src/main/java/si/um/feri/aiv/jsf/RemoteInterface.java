package si.um.feri.aiv.jsf;

import java.io.Serializable;
import java.rmi.RemoteException;

import jakarta.ejb.Remote;

@Remote
public interface RemoteInterface extends Remote, Serializable {
   public void connectPersonDoctor(String emailP, String emailD) throws RemoteException;
}
