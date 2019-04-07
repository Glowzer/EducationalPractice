import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public class ChatService {
    public interface ChatServer extends Remote {
        String prefix = "127.0.0.1";

        void showConnected(RemotePerson person) throws RemoteException;
        void showDisconnected(RemotePerson person) throws RemoteException;
        void addUser(RemotePerson person) throws RemoteException;
        void removeUser(RemotePerson person) throws RemoteException;
        Vector<RemotePerson> getUsers() throws RemoteException;
        void sendMessage(String message) throws RemoteException;
    }
}
