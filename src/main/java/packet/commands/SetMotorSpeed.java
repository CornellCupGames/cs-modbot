package packet.commands;

import java.util.ArrayList;

public class SetMotorSpeed extends Command {

	static {
		fields = new ArrayList<String>();
		fields.add("motor_id");
		fields.add("motor_speed");
	}

	public SetMotorSpeed() {
		this.values = new ArrayList<String>();
	}

	public int motor_id() {
		return Integer.parseInt(values.get(0));
	}

	public double motor_speed() {
		return Double.parseDouble(values.get(1));
	}
}