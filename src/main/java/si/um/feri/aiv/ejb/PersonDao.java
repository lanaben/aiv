package si.um.feri.aiv.ejb;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import si.um.feri.aiv.vao.Person;

import jakarta.ejb.Local;

@Local
public interface PersonDao extends Serializable {

	List<Person> getAll() throws Exception;
	Person find(String email) throws Exception;
	void save(Person o) throws Exception;
	void delete(String email) throws Exception;
	
}