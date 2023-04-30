package si.um.feri.aiv.ejb;

import java.util.ArrayList;
import si.um.feri.aiv.vmesni_nivo.Email;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import si.um.feri.aiv.vao.Person;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@Local(PersonDao.class)
public class PersonMemoryDao implements PersonDao {

	Logger log=Logger.getLogger(PersonMemoryDao.class.toString());

	@PersistenceContext(unitName = "demoUnit")
	EntityManager em;
	
	private static PersonMemoryDao instance = new PersonMemoryDao();
	
	public static PersonMemoryDao getInstance() {
		return instance;
	}
	
	private List<Person> people = Collections.synchronizedList(new ArrayList<Person>());

	@Override
	public List<Person> getAll() {
		log.info("DAO: get all");
		return em.createQuery("select o from Person o").getResultList();
	}
	
	@Override
	public Person find(int id) {
		log.info("EJB BEAN: najdi(id)");
		return em.find(Person.class, id);
	}

	@Override
	public Person find(String email)  {
		log.info("DAO: finding "+email);
		try {
			Query q = em.createQuery("select o from Person o where o.email = :e");
			q.setParameter("e", email);
			return (Person)q.getSingleResult();
		}
		catch (NoResultException e) {
			return new Person();
		}
	}
	
	@Override
	public void save(Person o, Boolean isSave)  {
		if (isSave) {
			log.info("DAO: saving "+o);
			log.info("Save mail");
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
		Query q = em.createQuery("select o from Person o where o.email = :e");
		q.setParameter("e", email);
		Person p = (Person)q.getSingleResult();
		em.remove(p);
	}
	
}