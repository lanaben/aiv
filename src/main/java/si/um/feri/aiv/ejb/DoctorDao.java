package si.um.feri.aiv.ejb;

import java.util.List;
import si.um.feri.aiv.vao.Doctor;

import jakarta.ejb.Local;

@Local
public interface DoctorDao {

	List<Doctor> getAll();
	Doctor find(String email);
	Doctor find(int id);
	void save(Doctor o);
	void delete(String email);
	
}