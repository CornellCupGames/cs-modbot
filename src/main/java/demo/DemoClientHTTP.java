import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoClientHTTP {

	public static String simpleExecuteGet(int portNumber, String urlMsg) {
		HttpURLConnection connection = null;
		String targetURL = "http://localhost:" + portNumber + "/" + urlMsg;
		try {
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static void test(int portNumber, int totalMessages, String urlMsgR) {
		System.out.println("starting client...");
		List<String> responses = new ArrayList<String>();
		long startTime = System.nanoTime();
		for (int i = 0; i < totalMessages; i++) {
			String urlMsg = urlMsgR + i;
			responses.add(simpleExecuteGet(portNumber, urlMsg));
		}
		long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Printing all messages...");
        for (String response : responses) {
        	System.out.println(response);
        }
        System.out.println("All messages sent!");
        System.out.println("Total elapsed time in nanoseconds: " + elapsedTime);
        System.out.println("Total messages sent: " + totalMessages);
        double frequency = ((double) totalMessages) / elapsedTime * Math.pow(10, 9);
        System.out.println("Message frequency: " + frequency + " Hz");
	}

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java DemoClient <port number> <test number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        int testNumber = Integer.parseInt(args[1]);

        switch (testNumber) {
        	case 1: 
        		test(portNumber, 1000, "simple/");
        		break;
        	case 2:
        		test(portNumber, 1000, "complex/");
        		break;
        	default:
        		test(portNumber, 1, "simple/");
        		break;
        }
    }
}