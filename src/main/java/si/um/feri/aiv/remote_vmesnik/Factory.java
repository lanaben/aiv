package si.um.feri.aiv.remote_vmesnik;

import si.um.feri.aiv.jsf.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Factory {
    Factory createFactory() throws Exception {
        return (Factory) Naming.lookup("//localhost/FirstLectures");
    }

}