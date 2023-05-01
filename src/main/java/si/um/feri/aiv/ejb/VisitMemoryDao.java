package si.um.feri.aiv.ejb;

import java.util.logging.Logger;
import java.util.List;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.aiv.vao.Visit;

@Stateless
@Local(VisitDao.class)
public class VisitMemoryDao implements VisitDao{

  Logger log = Logger.getLogger(VisitMemoryDao.class.toString());

	@PersistenceContext(unitName = "demoUnit")
	EntityManager em;

  private static VisitMemoryDao instance = new VisitMemoryDao();
	
	public static VisitMemoryDao getInstance() {
		return instance;
	}

  @Override
  public List<Visit> getAll() {
    return em.createQuery("select o from Visit o").getResultList();
  }

  @Override
  public Visit find(int id) {
    return em.find(Visit.class, id);
  }

  @Override
  public void save(Visit o) {
    em.persist(o);
  }

  @Override
  public void delete(int id) {
    Query q = em.createQuery("select o from Visit o where o.id = :e");
		q.setParameter("e", id);
		Visit p = (Visit)q.getSingleResult();
		em.remove(p);
  }
  
}
