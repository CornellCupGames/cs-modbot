package packet.commands;

import java.util.ArrayList;

public class Ping extends Command {

	static {
		fields = new ArrayList<String>();
	}

	public Ping(String... values) {
		this.values = new ArrayList<String>();
	}
}