import static spark.Spark.*;

public class DemoServerHTTP {

	public static void simpleRoute(int portNumber) {
        port(portNumber);
		get("/simple/:number", (req, res) -> {
			return "Message number: " + req.params(":number");
		});
	}

    public static void complexRoute(int portNumber) {
        port(portNumber);
        get("/complex/:number", (req,res) ->
            new DemoObjectHTTP(Integer.parseInt(req.params(":number"))),DemoObjectHTTP.rep());
    }

    public static void main(String[] args) {

    	//ensure a port number is used
    	if (args.length != 2) {
            System.err.println("Usage: java DemoServerHTTP <port number> <test num>");
            System.exit(1);
        }

        System.out.println("starting server...");
        int portNumber = Integer.parseInt(args[0]);

        int testNum = Integer.parseInt(args[1]);

        switch (testNum) {
        	case 1: 
        		simpleRoute(portNumber);
     			break;
            case 2:
                complexRoute(portNumber);
                break;
     		default:
     			simpleRoute(portNumber);
        }
        System.out.println("routes set up...");
    }
}