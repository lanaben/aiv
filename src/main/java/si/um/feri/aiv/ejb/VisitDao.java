package si.um.feri.aiv.ejb;

import java.util.List;
import si.um.feri.aiv.vao.Visit;

import jakarta.ejb.Local;

@Local
public interface VisitDao {
  List<Visit> getAll();
	Visit find(int id);
	void save(Visit o);
	void delete(int id);
}
