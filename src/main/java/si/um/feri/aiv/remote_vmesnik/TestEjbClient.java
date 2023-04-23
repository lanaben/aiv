package si.um.feri.aiv.remote_vmesnik;

import si.um.feri.aiv.jsf.AppJSFBean;

// import java.util.*;
// import javax.naming.Context;
// import javax.naming.InitialContext;
// import javax.naming.NamingException;

import java.rmi.Naming;

public class TestEjbClient  {

//   private static RemoteInterface lookupRemoteSavePerson() throws NamingException {
    
//     final Hashtable jndiProperties = new Hashtable();
//     jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//     final Context context = new InitialContext(jndiProperties);
//     return (RemoteInterface) context.lookup(
//      "ejb:/jboss-as-ejb-remote-server-side/AppJSFBean!" + RemoteInterface.class.getName()
//   );
// }

  public static void main(String[] args) throws Exception {
    System.out.println("Test");
    AppJSFBean App = (AppJSFBean)Naming.lookup("//localhost/FirstLectures");
    System.out.println("Test2");
  }
}
