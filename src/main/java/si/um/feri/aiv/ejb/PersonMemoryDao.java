package si.um.feri.aiv.ejb;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import si.um.feri.aiv.vmesni_nivo.Email;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


import si.um.feri.aiv.vao.Person;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
@Stateless
public class PersonMemoryDao extends UnicastRemoteObject implements PersonDaoRemote, PersonDao {

	public PersonMemoryDao() throws RemoteException {
	}

	Logger log=Logger.getLogger(PersonMemoryDao.class.toString());
	
	private static PersonMemoryDao instance;
	
	public static PersonMemoryDao getInstance() throws RemoteException {
		if(instance == null) {
			instance = new PersonMemoryDao();
		}
		return instance;
	}
	
	
	private List<Person> people = Collections.synchronizedList(new ArrayList<Person>());

	@Override
	public List<Person> getAll() throws RemoteException, Exception {
		log.info("DAO: get all");
		return people;
	}
	
	@Override
	public Person find(String email) throws Exception  {
		log.info("DAO: finding "+email);
		for (Person o : people)
			if (o.getEmail().equals(email))
				return o;
		return null;
	}
	
	@Override
	public void save(Person o) throws Exception  {
		log.info("DAO: saving "+o);
		if(find(o.getEmail())!=null) {
			log.info("DAO: editing "+o);
			delete(o.getEmail());
		}
		people.add(o);
	}
	
	@Override
	public void delete(String email) throws Exception {
		log.info("DAO: deleting "+email);
		for (Iterator<Person> i = people.iterator(); i.hasNext();) {
			if (i.next().getEmail().equals(email))
				i.remove();
		}
	}
	
}