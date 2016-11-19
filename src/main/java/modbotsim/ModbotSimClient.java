package modbotsim;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ModbotSimClient {

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

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java DemoClient <port number>");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int portNumber = Integer.parseInt(args[0]);

        while (true) {
        	String command = reader.readLine().trim();

        	if (command.equals("quit")) {
        		break;
        	}
        	System.out.println(simpleExecuteGet(portNumber, command));
        }
    }
}