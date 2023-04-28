package si.um.feri.aiv.ejb;

import java.util.List;
import si.um.feri.aiv.vao.Person;

import jakarta.ejb.Local;

@Local
public interface PersonDao {

	List<Person> getAll();
	Person find(String email);
	Person find(int id);
	void save(Person o);
	void delete(String email);
	
}