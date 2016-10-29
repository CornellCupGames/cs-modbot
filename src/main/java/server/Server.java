import static spark.Spark.*;
import connection.ConnectionType;
import connection.Connection;

public class Server {

    private Connection connection;
    private int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
        connection = null;
    }

	public static void pingRoute() {
		get("/ping", (req, res) -> {
            Packet p = new Packet();
			p.addCommand(CommandType.PING);
            Packet r = connection.transaction(p, ConnectionType.Timeout(30), true);
            if (r == null) {
                return "ping failed...";
            } else {
                return "ping received...";
            }
		});
	}

    public void run() {
        pingRoute();
    }

    public static void main(String[] args) {

    	//ensure a port number is used
    	if (args.length != 2) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        System.out.println("starting server...");
        int portNumber = Integer.parseInt(args[0]);

        Server s = new Server(portNumber);
        s.run();
    }
}