import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePerson extends Remote {
    void tell(String message) throws RemoteException;
    String getName() throws RemoteException;
}
