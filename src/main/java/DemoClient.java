import java.io.*;
import java.net.*;

/**Democlient to test server frequency
 * Created by CornellCup on 9/25/2016.
 */
public class DemoClient {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Usage: java DemoClient <hostname> <port number>");
            System.exit(1);
        }

        String hostname = args[0];
        int portNumber = Integer.parseInt(args[1]);

        int totalMessages = 1000;

        System.out.println("Starting client...");

        try (
            Socket echoSocket = new Socket(hostname, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        ) {
            long startTime = System.nanoTime();
            for (int i = 0; i < totalMessages; i++) {
                String testMessage = "Message: " + i;
                out.println(testMessage);
                System.out.printf("Sent message");
                System.out.println("echo: " + in.readLine());
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("All messages sent!");
            System.out.println("Total elapsed time in nanoseconds: " + elapsedTime);
            System.out.println("Total messages sent: " + totalMessages);
            double frequency = ((double) totalMessages) / elapsedTime;
            System.out.println("Message frequency: " + frequency);
        }
        catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostname);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("No I/O for connection to " + hostname);
            System.exit(1);
        }
    }
}