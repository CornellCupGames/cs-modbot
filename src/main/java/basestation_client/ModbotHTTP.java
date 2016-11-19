package basestation_client;

import basestation.bot.connection.Connection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ModbotHTTP extends Connection {

	int port;

	public ModbotHTTP(int port) {
		this.port = port;
	}

	public String simpleExecuteGet(String urlMsg) {
		HttpURLConnection connection = null;
		String targetURL = "http://localhost:" + port + "/" + urlMsg;
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

	public void ping() {
		simpleExecuteGet("ping");
	}

	public void setMotorSpeeds(double fl, double fr, double bl, double br) {
		simpleExecuteGet("setmotorspeeds/" + fl + "/" + fr + "/" + bl + "/" + br);
	}

	public void destroy() {
		//do nothing
	}

	public boolean connectionActive() {
		return true;
	}
}