package si.um.feri.aiv.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import si.um.feri.aiv.vao.Doctor;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DoctorMemoryDao implements DoctorDao {

	Logger log = Logger.getLogger(DoctorMemoryDao.class.toString());
	
	private static DoctorMemoryDao instance = new DoctorMemoryDao();

	@PersistenceContext(unitName = "demoUnit")
	EntityManager em;
	
	public static DoctorMemoryDao getInstance() {
		return instance;
	}
	
	private List<Doctor> doctors = Collections.synchronizedList(new ArrayList<Doctor>());

	@Override
	public List<Doctor> getAll() {
		log.info("DAO: get all");
		return em.createQuery("select o from Doctor o").getResultList();
	}

	@Override
	public Doctor find(int id) {
		log.info("EJB BEAN: najdi(id)");
		return em.find(Doctor.class, id);
	}
	
	@Override
	public Doctor find(String email)  {
		try {
			log.info("DAO: finding "+email);
			Query q = em.createQuery("select o from Doctor o where o.email = :e");
			q.setParameter("e", email);
			return (Doctor)q.getSingleResult();
		}
		catch (NoResultException e) {
			return new Doctor();
		}		
	}
	
	@Override
	public void save(Doctor o, Boolean isSave)  {
		if (isSave) {
			log.info("DAO: saving "+o);
			log.info("Save mail");
			log.info("Moj save doctor");
			log.info(o.getId());
			em.persist(o);
		}
		else {
			log.info("Refresh mail");
			em.merge(o);
		}
	}
	
	@Override
	public void delete(String email) {
		log.info("DAO: deleting "+email);
		Query q = em.createQuery("select o from Doctor o where o.email = :e");
		q.setParameter("e", email);
		Doctor p = (Doctor)q.getSingleResult();
		em.remove(p);
	}
	
}