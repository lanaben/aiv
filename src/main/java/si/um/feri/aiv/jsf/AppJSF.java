package si.um.feri.aiv.jsf;


import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AppJSF extends Remote, Serializable {

  void connectPersonDoctor(String emailP, String emailD) throws RemoteException;

}