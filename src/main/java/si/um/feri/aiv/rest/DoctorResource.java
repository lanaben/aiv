package si.um.feri.aiv.rest;

import jakarta.ejb.EJB;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.aiv.ejb.DoctorDao;
import si.um.feri.aiv.ejb.DoctorMemoryDao;
import si.um.feri.aiv.vao.Doctor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("/zdravniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {

	@EJB
	private DoctorDao daoDoctor = DoctorMemoryDao.getInstance();

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/info")
	public String info() {
		return "DoctorResource";
	}

	@GET
	public Collection<Doctor> getAllDoctors() {
		 Collection<Doctor> collection = new ArrayList<Doctor>(daoDoctor.getAll());
		 return daoDoctor.getAll();
	}

	@GET
	@Path("/{email}")
	public Doctor getDoctor(@PathParam("email") String email) {
		return daoDoctor.find(email);
	}

	@POST
	public void addDoctor(Doctor o) throws Exception {
		daoDoctor.save(o, true);
	}

	@PUT
	@Path("/{email}")
	public void spremeniDoctorja(Doctor o,@PathParam("email") String email) throws Exception {
		if (daoDoctor.find(email)==null)
			throw new Exception("Doctor še ne obstaja.");
		daoDoctor.save(o, false);
	}
}
