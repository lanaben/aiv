package si.um.feri.aiv.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import si.um.feri.aiv.vao.Doctor;

import jakarta.ejb.Stateless;

@Stateless
public class DoctorMemoryDao implements DoctorDao {

	Logger log = Logger.getLogger(DoctorMemoryDao.class.toString());
	
	private static DoctorMemoryDao instance = new DoctorMemoryDao();
	
	public static DoctorMemoryDao getInstance() {
		return instance;
	}
	
	private List<Doctor> doctors = Collections.synchronizedList(new ArrayList<Doctor>());

	@Override
	public List<Doctor> getAll() {
		log.info("DAO: get all");
		return doctors;
	}
	
	@Override
	public Doctor find(String email)  {
		log.info("DAO: finding "+email);
		for (Doctor o : doctors)
			if (o.getEmail().equals(email))
				return o;
		return null;
	}
	
	@Override
	public void save(Doctor o)  {
		log.info("DAO: saving "+o);
		if(find(o.getEmail())!=null) {
			log.info("DAO: editing "+o);
			delete(o.getEmail());
		}
		doctors.add(o);
	}
	
	@Override
	public void delete(String email) {
		log.info("DAO: deleting "+email);
		for (Iterator<Doctor> i = doctors.iterator(); i.hasNext();) {
			if (i.next().getEmail().equals(email))
				i.remove();
		}
	}
	
}