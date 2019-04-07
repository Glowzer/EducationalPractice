import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class ChatClient {
    private static Map<String, Byte> commands = new TreeMap<>();

    private static ChatService.ChatServer server;
    private static PrintWriter out;
    private static RemotePerson person;

    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                String hostname = args[0];
                String mudname = args[1];

                server = (ChatService.ChatServer) Naming.lookup("rmi://" + hostname + ":" + Protocol.PORT + "/" + mudname);
                System.out.println("Welcome to " + mudname);

                String name = getLine("Enter your name: ");
                out = new PrintWriter(System.out);
                person = new Person(name, out);

                int priority = Thread.currentThread().getPriority();
                Thread.currentThread().setPriority(priority - 1);

                server.showConnected(person);
                session();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("Usage: java ChatClient <host> <mudname>");
            }
        } else {
            System.err.println("Invalid number of arguments!");
        }
    }

    private static void session() throws RemoteException {
        for (; ; ) {
            try {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }

                String line = getLine("> ");
                String cmd;
                String arg;
                int i = line.indexOf(' ');
                if (i == -1) {
                    cmd = line;
                    arg = null;
                } else {
                    cmd = line.substring(0, i).toLowerCase();
                    arg = line.substring(i + 1);
                }
                if (arg == null) {
                    arg = "";
                }

                processCommand(cmd, arg);
            } catch (RemoteException e) {
                System.err.println("Perhaps the server has crashed");
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String getLine(String prompt) {
        String line = null;
        do {
            try {
                System.out.print(prompt);
                System.out.flush();
                line = in.readLine();
                if (line != null) {
                    line = line.trim();
                }
            } catch (Exception ignored) {
            }
        } while (line == null || line.length() == 0);
        return line;
    }

    private static byte translateCmd(String cmd) {
        Byte command = commands.get(cmd.trim());
        return command == null ? 0 : (byte) command;
    }

    private static void printHelp() {
        System.out.println("> Available commands: " + HELP);
    }

    private static void processCommand(String cmd, String arg) throws RemoteException {
        byte id = translateCmd(cmd);

        switch (id) {
            case Protocol.CMD_DISCONNECT:
                System.out.println("Bye...");
                server.showDisconnected(person);
                System.exit(0);
            case Protocol.CMD_USER:
                printUsers(server.getUsers());
                break;
            case Protocol.CMD_LETTER:
                if (!arg.isEmpty()) {
                    server.sendMessage(person.getName() + ": " + arg);
                } else {
                    System.out.println("> Cannot send empty message");
                }
                break;
            case Protocol.CMD_HELP:
                printHelp();
                break;
            default:
                System.err.println("> Unknown command, try 'h' or 'help'");
        }
    }

    private static void printUsers(Vector<RemotePerson> users) throws RemoteException {
        if (users != null) {
            System.out.println("> Users: ");
            for (RemotePerson user : users) {
                System.out.println("\t" + user.getName());
            }
        }
    }

    static {
        commands.put("q", Protocol.CMD_DISCONNECT);
        commands.put("quit", Protocol.CMD_DISCONNECT);
        commands.put("u", Protocol.CMD_USER);
        commands.put("users", Protocol.CMD_USER);
        commands.put("s", Protocol.CMD_LETTER);
        commands.put("send", Protocol.CMD_LETTER);
        commands.put("h", Protocol.CMD_HELP);
        commands.put("help", Protocol.CMD_HELP);
    }

    private static final String HELP = "(q)uit / (u)sers / (s)end [message] / (h)elp";
}
