package si.um.feri.aiv.ejb;

import jakarta.ejb.Stateless;

@Stateless
public class TestStatelessEjb {
  public String sayHello(String name) {
    return "Hello, " + name + "!";
  }
  
  
}



