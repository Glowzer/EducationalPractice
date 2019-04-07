import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ChatServer extends UnicastRemoteObject implements ChatService.ChatServer, Serializable {
    private static final long serialVersionUID = 7453281245880199453L;

    private String mudname;

    protected ChatServer() throws RemoteException {
    }

    public ChatServer(String mudname) throws RemoteException {
        this.mudname = mudname;
    }

    public static void main(String[] args) {
        System.out.println("Binding to port " + Protocol.PORT + ", please wait...");
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Invalid number of arguments!");
            }
            ChatServer server = new ChatServer(args[0]);
            LocateRegistry.createRegistry(Protocol.PORT);
            Naming.rebind("rmi://" + ChatService.ChatServer.prefix + ":" + Protocol.PORT + "/" + server.mudname, server);
            System.out.println("Server started");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Usage: java ChatServer <username>");
        }
    }

    static final transient Vector<String> names = new Vector<>();
    static final transient Vector<RemotePerson> users = new Vector<>();

    protected void tellEveryone(final String message) {
        Vector<RemotePerson> usersVector = (Vector<RemotePerson>) users.clone();
        synchronized (names) {
            for (RemotePerson person : usersVector) {
                try {
                    person.tell(message);
                } catch (RemoteException e) {
                    try {
                        removeUser(person);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    @Override
    public void showConnected(RemotePerson person) throws RemoteException {
        addUser(person);
        tellEveryone(person.getName() + " connected");
    }

    @Override
    public void showDisconnected(RemotePerson person) throws RemoteException {
        removeUser(person);
        tellEveryone(person.getName() + " disconnected");
    }

    @Override
    public void addUser(RemotePerson person) throws RemoteException {
        synchronized (names) {
            names.add(person.getName());
            users.add(person);
        }
    }

    @Override
    public void removeUser(RemotePerson person) throws RemoteException {
        synchronized (names) {
            int i = users.indexOf(person);
            if (i == -1) {
                return;
            }
            names.remove(person.getName());
            users.remove(person);
        }
    }

    @Override
    public Vector<RemotePerson> getUsers() throws RemoteException {
        return users;
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        tellEveryone(message);
    }
}
