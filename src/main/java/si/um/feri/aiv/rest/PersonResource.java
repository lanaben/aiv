package si.um.feri.aiv.rest;

import jakarta.ejb.EJB;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.aiv.ejb.DoctorDao;
import si.um.feri.aiv.ejb.DoctorMemoryDao;
import si.um.feri.aiv.ejb.PersonDao;
import si.um.feri.aiv.ejb.PersonMemoryDao;
import si.um.feri.aiv.vao.Doctor;
import si.um.feri.aiv.vao.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Path("/pacienti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

	@EJB
	private PersonDao daoPerson = PersonMemoryDao.getInstance();

	@EJB
	private DoctorDao daoDoctor = DoctorMemoryDao.getInstance();

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/info")
	public String info() {
		return "PersonResource";
	}

	@GET
	public Collection<Person> getAllPeople() {
		return daoPerson.getAll();
	}

	@GET
	@Path("/{email}")
	public Person getPerson(@PathParam("email") String email) {
		return daoPerson.find(email);
	}

	@POST
	public void addPerson(Person o) throws Exception {
		if (o.getPatientsDoctor() != null) {
			Doctor doc = daoDoctor.find(o.getPatientsDoctor().getEmail());
			o.setPatientsDoctor(doc);
		} 
		daoPerson.save(o, true);
	}
}
