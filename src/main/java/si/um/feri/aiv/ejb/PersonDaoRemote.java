package si.um.feri.aiv.ejb;

import java.io.Serializable;
import java.util.List;
import si.um.feri.aiv.vao.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;

@jakarta.ejb.Remote
public interface PersonDaoRemote extends Remote, Serializable {
  List<Person> getAll() throws RemoteException, Exception;

}
