import java.net.*;
import java.io.*;
import java.util.Scanner;

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
             Scanner in = new Scanner(clientSocket.getInputStream());
        ) {
            String inputLine;
            while (true) {
                System.out.println("Waiting for input");
                inputLine = in.nextLine();
                System.out.println("Got Input");
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("IO Exception on port " + portNumber);
            System.out.println(e.getMessage());
        }
    }

}