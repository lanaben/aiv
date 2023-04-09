package si.um.feri.aiv.opazovalci;

import si.um.feri.aiv.vao.Person;
import si.um.feri.aiv.vao.Doctor;

public interface IObserver {

	public void update(Person p);
	public void update(Person p, Doctor d);
}