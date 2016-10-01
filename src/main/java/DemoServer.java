import java.net.*;
import java.io.*;

/**Demo server (code should go on the pi)
 * Created by CornellCup on 9/25/2016.
 */
public class DemoServer {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java DemoServer <port number>");
            System.exit(1);
        }

        System.out.println("Starting server...");

        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            out.println("Hello world");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("in loop");
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("IO Exception on port " + portNumber);
            System.out.println(e.getMessage());
        }
    }

}