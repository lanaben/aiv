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

	//Dao dao= InMemoryDao.getInstance();

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
		return daoDoctor.getAll();
	}

	@GET
	@Path("/{email}")
	public Doctor getDoctor(@PathParam("email") String email) {
		return daoDoctor.find(email);
	}

	// @GET
	// @Path("/ext/{email}")
	// public DoctorExtended getDoctorExtended(@PathParam("email") String email) {
	// 	return new DoctorExtended(dao.findDoctor(email),dao.findDoctor(email));
	// }

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


	// @GET
	// @Path("/")
	// public List<DoctorExtended> getVseRacune() {
	// 	List<DoctorExtended> ret= new ArrayList<>();
	// 	for (si.um.feri.banka.vao.Person br : dao.vrniVseRacune())
	// 		ret.add(new DoctorExtended(br));
	// 	return ret;
	// }

	// @GET
	// @Path("/{iban}")
	// public DoctorExtended getRacun(@PathParam("iban") String iban) {
	// 	return new DoctorExtended(dao.najdiBancniRacun(iban));
	// }

	// @POST
	// @Path("/")
	// public void dodajBancniRacun(DoctorExtended br) throws Exception {
	// 	dao.shrani(br.asVao());
	// }

	// @PUT
	// @Path("/{iban}")
	// public void spremeniBancniRacun(DoctorExtended br,@PathParam("iban") String iban) throws Exception {
	// 	if (dao.najdiBancniRacun(iban)==null)
	// 		throw new Exception("Bančni račun še ne obstaja.");
	// 	dao.shrani(br.asVao());
	// }

}
