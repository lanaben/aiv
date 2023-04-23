package si.um.feri.aiv.ejb;

import jakarta.ejb.Remote;

@Remote
public interface TestStatelessEjbRemote {
  String sayHello(String name);
}