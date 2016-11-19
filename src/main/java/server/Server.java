package server;
import static spark.Spark.*;
import connection.ConnectionType;
import connection.ConnectionECE;
import modbotsim.ModbotSimConnection;
import packet.Packet;

public class Server {

    private ConnectionECE connection;
    private int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
        port(portNumber);
        connection = new ModbotSimConnection();
    }

	public void pingRoute() {
		get("/ping", (req, res) -> {
            Packet p = new Packet();
			p.addCommand(Packet.CommandType.PING);
            Packet r = connection.transaction(p, ConnectionType.Timeout(30), true);
            if (r == null) {
                return "ping failed...";
            } else {
                return "ping received...";
            }
		});
	}

    public void setMotorsRoute() {
        get("/setmotors/:fl/:fr/:bl/:br", (req, res) -> {
            Packet p = new Packet();
            Double fl = Double.parseDouble(req.params(":fl"));
            Double fr = Double.parseDouble(req.params(":fr"));
            Double bl = Double.parseDouble(req.params(":bl"));
            Double br = Double.parseDouble(req.params(":br"));
            p.addCommand(Packet.CommandType.SET_MOTOR_SPEED, "1", fl.toString());
            p.addCommand(Packet.CommandType.SET_MOTOR_SPEED, "2", fr.toString());
            p.addCommand(Packet.CommandType.SET_MOTOR_SPEED, "3", bl.toString());
            p.addCommand(Packet.CommandType.SET_MOTOR_SPEED, "4", br.toString());
            Packet r = connection.transaction(p, ConnectionType.Timeout(30), true);
            if (r == null) {
                return "setting speeds failed...";
            } else {
                return "setting speeds success...";
            }
        });
    }

    public void run() {
        pingRoute();
        setMotorsRoute();
    }

    public static void main(String[] args) {

    	//ensure a port number is used
    	if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        System.out.println("starting server at port: " + portNumber);
        Server s = new Server(portNumber);
        s.run();
    }
}