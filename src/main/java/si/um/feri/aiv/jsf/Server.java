package si.um.feri.aiv.jsf;

import java.rmi.Naming;
import java.rmi.Remote;

public class Server {
  public static void main(String[] args) throws Exception {
		AppJSFBean c = new AppJSFBean();
		Naming.bind("//localhost/FirstLectures", c);
	}
}
