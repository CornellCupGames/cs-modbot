import java.util.ArrayList;

public class SetMotorSpeed extends Command {

	static {
		fields = new ArrayList<String>();
		fields.add("motor_id");
		fields.add("motor_speed");
	}

	public SetMotorSpeed(String... values) {
		values = new ArrayList<String>();
		for (String value : values) {
			values.add(value);
		}
	}
}