import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Person extends UnicastRemoteObject implements RemotePerson {
    private String name;
    private PrintWriter tellStream;

    protected Person() throws RemoteException {
    }

    public Person(String name, PrintWriter tellStream) throws RemoteException {
        this.name = name;
        this.tellStream = tellStream;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void tell(String message) throws RemoteException {
        tellStream.println(message);
        tellStream.flush();
    }
}
